package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.PilgrimagePower;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pilgrimage extends BaseCard {
    public static final String ID = makeID(Pilgrimage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public Pilgrimage() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Appliquer PilgrimagePower pour 1 tour
        addToBot(new ApplyPowerAction(p, p, new PilgrimagePower(p, 1), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0); // Réduire le coût à 0 une fois amélioré
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pilgrimage();
    }
}
