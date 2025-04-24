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
import bloodandhell.powers.RejuvenatingBreathPower;

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
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Appliquer le Power pour recalculer dynamiquement le coût de la carte
        addToBot(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, new RejuvenatingBreathPower(p, this), 1));

        // Soigner et ajouter de l'armure
        addToBot(new HealAction(p, p, this.magicNumber));
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = "Heal " + (HEAL_AMOUNT + UPGRADE_HEAL) + " HP and gain " + (BLOCK_AMOUNT + UPGRADE_BLOCK) + " Block.";
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RejuvenatingBreath();
    }
}
