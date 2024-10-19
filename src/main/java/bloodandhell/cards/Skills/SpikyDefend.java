package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import bloodandhell.powers.LoseThornsPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
// Spikes VFX

public class SpikyDefend extends BaseCard {
    public static final String ID = makeID(SpikyDefend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            3
    );

    private static final int BLOCK = 30;
    private static final int UPG_BLOCK = 35;

    private static final int THORNS = 3;
    private static final int UPG_THORNS = 5;

    public SpikyDefend() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(THORNS, UPG_THORNS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));

        //AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.VFXAction(new SelfSpikesEffect(Color.GOLDENROD, 5, true, 0.75F)));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseThornsPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpikyDefend();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK - BLOCK);
            upgradeMagicNumber(UPG_THORNS - THORNS);
        }
    }
}
