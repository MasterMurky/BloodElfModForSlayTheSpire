package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ColdBlood extends BaseCard {
    public static final String ID = makeID(ColdBlood.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );


    private static final int MN = 3;
    private static final int UPG_MN = 1; //the number added when upgraded

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;


    public ColdBlood() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setMagic(MN, UPG_MN); //Sets the card's MN and how much it changes when upgraded.
        setBlock(BLOCK, UPG_BLOCK);
            }

    public ColdBlood(String ID, CardStats info) {
        super(ID, info);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        addToBot(new GainBlockAction(p, p, this.block));
        if (GameActionManager.damageReceivedThisTurn > 0) {
            p.heal(this.magicNumber);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (GameActionManager.damageReceivedThisTurn > 0){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();}
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new ColdBlood();
    }
}
