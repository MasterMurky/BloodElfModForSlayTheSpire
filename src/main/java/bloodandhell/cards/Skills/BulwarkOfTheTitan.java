package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BulwarkOfTheTitan extends BaseCard {
    public static final String ID = makeID(BulwarkOfTheTitan.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2
    );

    private static final int BASE_MULTIPLIER = 2;
    private static final int UPGRADE_MULTIPLIER = 3;

    public BulwarkOfTheTitan() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int strength = 0;
        if (p.hasPower(StrengthPower.POWER_ID)) {
            strength = p.getPower(StrengthPower.POWER_ID).amount;
        }

        int multiplier = this.upgraded ? UPGRADE_MULTIPLIER : BASE_MULTIPLIER;
        int blockAmount = strength * multiplier;

        if (blockAmount > 0) {
            addToBot(new GainBlockAction(p, p, blockAmount));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new BulwarkOfTheTitan();
    }
}
