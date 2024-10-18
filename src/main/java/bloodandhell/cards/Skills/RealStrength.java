package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RealStrength extends BaseCard {
    public static final String ID = makeID(RealStrength.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public RealStrength() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int strengthAmount = 0;

        if (p.hasPower(LoseStrengthPower.POWER_ID)) {
            strengthAmount += p.getPower(LoseStrengthPower.POWER_ID).amount;
        }

        if (strengthAmount < 0) {
            strengthAmount = 0;
        }

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, (strengthAmount))));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RealStrength();
    }
}
