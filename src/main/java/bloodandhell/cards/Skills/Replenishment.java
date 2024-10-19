package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Replenishment extends BaseCard {
    public static final String ID = makeID(Replenishment.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // Cost of the card
    );

    // Constants for the magic number and its upgrade
    private static final int MN = 3; // Base amount of strength to gain
    private static final int UPG_MN = 1; // Increment of strength when the card is upgraded

    public Replenishment() {
        super(ID, info);
        setMagic(MN, UPG_MN); // Set the magic number (strength gained) and upgrade increment
        this.exhaust = true;  // The card will exhaust after being played
    }

    public Replenishment(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Draw 1 card
        addToBot(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                // Check if a card was drawn
                if (DrawCardAction.drawnCards.size() > 0) {
                    AbstractCard drawnCard = DrawCardAction.drawnCards.get(0);

                    // If the drawn card is upgraded, gain strength based on the magic number
                    if (drawnCard.upgraded) {
                        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
                    } else {
                        // If not upgraded, upgrade the drawn card
                        addToBot(new UpgradeSpecificCardAction(drawnCard));
                    }
                }
                this.isDone = true;
            }
        }));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName(); // Upgrade the card's name
            upgradeMagicNumber(UPG_MN); // Increase the magic number when the card is upgraded
            this.exhaust = false; // When upgraded, the card no longer exhausts
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Replenishment();
    }
}
