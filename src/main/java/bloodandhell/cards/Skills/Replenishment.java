package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Replenishment extends BaseCard {
    public static final String ID = makeID(Replenishment.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Replenishment() {
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(this.magicNumber, new HandCheckAction() {
            @Override
            public void update() {
                AbstractDungeon.player.hand.refreshHandLayout();

                // Only 1
                if (!upgraded) {
                    for (AbstractCard card : p.hand.group) {
                        if (card.name.contains("Defend") && !card.freeToPlayOnce) {
                            card.freeToPlayOnce = true;
                            break;
                        }
                    }
                } else {
                    // All of them (no break)
                    for (AbstractCard card : p.hand.group) {
                        if (card.name.contains("Defend") && !card.freeToPlayOnce) {
                            card.freeToPlayOnce = true;
                        }
                    }
                }

                this.isDone = true;
            }
        }));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Replenishment();
    }
}
