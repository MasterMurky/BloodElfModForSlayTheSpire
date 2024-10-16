package bloodandhell.cards.Powers;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.MorphingPower;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NoOneWillPass extends BaseCard {
    public static final String ID = makeID(NoOneWillPass.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private static final int upg_magicNumber = 2; //l'UPG du MN va ici mais le MN va dans la méthode juste en dessous


    public NoOneWillPass() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.magicNumber = this.baseMagicNumber = 3;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted enemy. m =null si aucun ennemi n'est pointé)
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new MorphingPower(ID, AbstractPower.PowerType.BUFF, false, p, p, this.magicNumber)));
    }

    public void upgrade () {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(upg_magicNumber); //on utilise upg_MN à la place de MG lorsque la carte est améliorée
        }
    }
}