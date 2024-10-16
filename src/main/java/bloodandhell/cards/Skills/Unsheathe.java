package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Unsheathe extends BaseCard {
    public static final String ID = makeID(Unsheathe.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int MN = 0;
    private static final int UPG_MN = 1;

    public Unsheathe() {
        super(ID, info);
        setMagic(MN, UPG_MN);  // Initialisation du magicNumber ici
    }

    public Unsheathe(String ID, CardStats info) {
        super(ID, info);
        setMagic(MN, UPG_MN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Pioche 1 carte
        this.addToBot(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard drawnCard = AbstractDungeon.player.hand.getTopCard();
                int cardCost = drawnCard.costForTurn; // Récupère le coût de la carte piochée

                // Applique de la force temporaire égale au coût de la carte piochée + magicNumber
                if (cardCost > 0) {
                    addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, (cardCost + Unsheathe.this.magicNumber)), cardCost));
                    addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, (cardCost + Unsheathe.this.magicNumber)), cardCost));
                }

                isDone = true;
            }
        }));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Unsheathe();
    }
}
