package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PropheticWritings extends BaseCard {
    public static final String ID = makeID(PropheticWritings.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    private static final int upg_magicNumber = 1; //l'UPG du MN va ici mais le MN va dans la méthode juste en dessous

    public PropheticWritings() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public PropheticWritings(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        addToBot(new LoseHPAction(p, p, 1));
        addToBot((AbstractGameAction) new ExpertiseAction((AbstractCreature) p, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(upg_magicNumber); //on utilise upg_MN à la place de MG lorsque la carte est améliorée
        }
    }


    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new PropheticWritings();
    }

}


