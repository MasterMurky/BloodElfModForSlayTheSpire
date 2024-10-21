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

    private static final int UPGRADE_BONUS_STRENGTH = 1;

    public Unsheathe() {
        super(ID, info);
    }

    public Unsheathe(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Pioche 1 carte
        this.addToBot(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard drawnCard = AbstractDungeon.player.hand.getTopCard();
                int cardCost = drawnCard.costForTurn;

                int strengthToApply = cardCost;

                // *2 if Upgraded
                if (Unsheathe.this.upgraded) {
                    strengthToApply = strengthToApply*2;
                }

                if (cardCost > 0) {
                    addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthToApply), strengthToApply));
                    addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, strengthToApply), strengthToApply));
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
