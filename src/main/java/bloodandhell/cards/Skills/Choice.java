package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.DefendNow;
import bloodandhell.powers.StrikeNow;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Choice extends BaseCard {
    public static final String ID = makeID(Choice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );


    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3; //c'est la quantité de block obtenu lors de l'upgrade




    public Choice() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE,UPG_DAMAGE);
        setBlock(BLOCK,UPG_BLOCK);
    }

    public Choice(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new StrikeNow());
        stanceChoices.add(new DefendNow());
        if (this.upgraded)
            for (AbstractCard c : stanceChoices)
                c.upgrade();
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
    }



    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new Choice();
    }
}
