package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Catharsis extends BaseCard {
    public static final String ID = makeID(Catharsis.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Catharsis() {
        super(ID, info);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Réinitialise la force à zéro (annule les bonus et les malus)
        int strengthAmount = p.getPower(StrengthPower.POWER_ID) != null ? p.getPower(StrengthPower.POWER_ID).amount : 0;
        if (strengthAmount != 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -strengthAmount), -strengthAmount));
        }

        // Réinitialise la dextérité à zéro (annule les bonus et les malus)
        int dexterityAmount = p.getPower(DexterityPower.POWER_ID) != null ? p.getPower(DexterityPower.POWER_ID).amount : 0;
        if (dexterityAmount != 0) {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -dexterityAmount), -dexterityAmount));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Catharsis();
    }
}
