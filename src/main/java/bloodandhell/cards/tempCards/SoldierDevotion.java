package bloodandhell.cards.tempCards;

import basemod.AutoAdd;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore

public class SoldierDevotion extends BaseCard {
    public static final String ID = makeID(SoldierDevotion.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public SoldierDevotion() {
        super(ID, info);
        setMagic(DRAW, UPG_DRAW); // nombre de cartes piochées
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoldierDevotion();
    }
}
