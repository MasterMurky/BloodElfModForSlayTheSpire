package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.AvaricePower;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Avarice extends BaseCard {
    public static final String ID = makeID(Avarice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    public Avarice() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.exhaust = true;
            }

    public Avarice(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        addToBot((AbstractGameAction)new AvaricePower(p, m, this.energyOnUse));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false; // Remove the Exhaust status when upgraded.
            this.rawDescription = "Lose 1HP, Heal !M!HP, Gain 1 [E], X times."; // Facultatif : mettre à jour la description si nécessaire
            this.initializeDescription(); // Update the interface to make the changes appear.
        }
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new Avarice();
    }
}