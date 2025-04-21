package bloodandhell.cards.tempCards;

import basemod.AutoAdd;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore

public class NotASoldier extends BaseCard {
    public static final String ID = makeID(NotASoldier.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    private static final int HEAL = 5;
    private static final int UPG_HEAL = 2;

    public NotASoldier() {
        super(ID, info);
        setMagic(HEAL, UPG_HEAL);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new NotASoldier();
    }
}
