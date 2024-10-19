package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.DoubleImpactPower;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleImpact extends BaseCard {
    public static final String ID = makeID(DoubleImpact.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public DoubleImpact() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean affectsSkills = this.upgraded;
        addToBot(new ApplyPowerAction(p, p, new DoubleImpactPower(p, 1, affectsSkills), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = "All Attacks and Spells you play this turn are played twice.";
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleImpact();
    }
}
