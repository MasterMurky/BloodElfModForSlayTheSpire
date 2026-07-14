package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.FacingDestinyPower;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FacingDestiny extends BaseCard {
    public static final String ID = makeID(FacingDestiny.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    private static final int MN = 15;
    private static final int UPG_MN = 5;
    private static final int IMMUNITY_TURNS = 2;

    public FacingDestiny() {
        super(ID, info);
        setMagic(MN, UPG_MN);
        setExhaust(true);
    }

    public FacingDestiny(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.currentHealth > 1) {
            addToBot(new LoseHPAction(p, p, p.currentHealth - 1));
        }
        addToBot(new ApplyPowerAction(p, p, new FacingDestinyPower(p, IMMUNITY_TURNS, this.magicNumber), IMMUNITY_TURNS));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FacingDestiny();
    }
}
