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
        // PilgrimagePower removes debuffs the first time its atEndOfTurn fires with amount==1.
        // Starting at 2 turns means: end of THIS turn -> amount 2->1 (no removal yet), end of
        // NEXT turn -> amount==1 -> removal fires. Starting at 1 would remove debuffs at the
        // end of the same turn Pilgrimage was played, one turn too early.
        addToBot(new ApplyPowerAction(p, p, new PilgrimagePower(p, 2), 2));
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
