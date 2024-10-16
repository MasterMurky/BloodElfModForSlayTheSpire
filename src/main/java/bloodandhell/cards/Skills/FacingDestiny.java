// NOT DONE YET
package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FacingDestiny extends BaseCard {
    public static final String ID = makeID(FacingDestiny.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    private static final int MN = 15;
    private static final int UPG_MN = 5; //c'est la quantité de block obtenu lors de l'upgrade


    public FacingDestiny() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setMagic(MN, UPG_MN); //Sets the card's MN and how much it changes when upgraded.
            }

    public FacingDestiny(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        for (int i = 0; i < 3; i++) {
            addToBot(new LoseHPAction(p, p, 1));
        }
        addToBot(new HealAction(p, p, MN)); //addToBot ajoute l'action à faire à la fin de l'action queue  (source, target, amount)
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new FacingDestiny();
    }
}
