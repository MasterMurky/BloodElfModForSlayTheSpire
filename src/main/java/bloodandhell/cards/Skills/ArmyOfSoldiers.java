package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.cards.tempCards.SoldierDefend;
import bloodandhell.cards.tempCards.SoldierDevotion;
import bloodandhell.cards.tempCards.SoldierStrike;
import bloodandhell.cards.tempCards.NotASoldier;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Random;

public class ArmyOfSoldiers extends BaseCard {
    public static final String ID = makeID(ArmyOfSoldiers.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    private static final int CARDS_TO_ADD = 3;
    private static final int UPG_CARDS_TO_ADD = 2;

    public ArmyOfSoldiers() {
        super(ID, info);
        setMagic(CARDS_TO_ADD, UPG_CARDS_TO_ADD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Random rng = new Random();

        for (int i = 0; i < this.magicNumber; i++) {
            AbstractCard card;
            int roll = rng.nextInt(50); //  0 a 49

            if (roll == 0) {
                card = new NotASoldier(); //
            } else {
                ArrayList<AbstractCard> pool = new ArrayList<>();
                pool.add(new SoldierStrike());
                pool.add(new SoldierDefend());
                pool.add(new SoldierDevotion());

                card = pool.get(rng.nextInt(pool.size())).makeCopy();
            }

            addToBot(new MakeTempCardInHandAction(card, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArmyOfSoldiers();
    }
}
