package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RejuvenatingBreath extends BaseCard {
    public static final String ID = makeID(RejuvenatingBreath.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int HEAL_AMOUNT = 10;
    private static final int BLOCK_AMOUNT = 10;
    private static final int UPGRADE_HEAL = 2;
    private static final int UPGRADE_BLOCK = 2;

    public RejuvenatingBreath() {
        super(ID, info);
        setMagic(HEAL_AMOUNT, UPGRADE_HEAL);
        setBlock(BLOCK_AMOUNT, UPGRADE_BLOCK);
        setExhaust(true);
        // Dynamic cost (units digit of current HP) is handled every frame by
        // bloodandhell.util.DynamicCostSync, the same mechanism PainWard/BloodhuntsEnd/
        // QuickCuts/BloodGambit use -- applying it via a Power at play time (the previous
        // approach) ran only after the card's own cost had already been charged, and never
        // actually took effect.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Soigner et ajouter de l'armure
        addToBot(new HealAction(p, p, this.magicNumber));
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade(); // Applique le gain de PV/Bloc et le texte UPGRADE_DESCRIPTION (avec !M!/!B!).
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RejuvenatingBreath();
    }
}
