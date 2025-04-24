package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class MobileFortress extends BaseCard {
    public static final String ID = makeID(MobileFortress.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    private static final int PLATED_ARMOR = 4;
    private static final int UPGRADED_PLATED_ARMOR = 2;
    private static final int DEX_LOSS = 4;

    public MobileFortress() {
        super(ID, info);
        setMagic(PLATED_ARMOR, UPGRADED_PLATED_ARMOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Applique l'armure plaquée
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
        // Réduit la dextérité
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.DEX_LOSS), this.DEX_LOSS));

    }

    @Override
    public AbstractCard makeCopy() {
        return new MobileFortress();
    }
}
