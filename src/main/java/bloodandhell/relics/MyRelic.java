package bloodandhell.relics;

import bloodandhell.character.MyCharacter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static bloodandhell.BasicMod.makeID;

public class MyRelic extends BaseRelic {
    private static final String NAME = "MyRelic"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    // STARTER, COMMON, UNCOMMON, RARE, SHOP, BOSS, SPECIAL (for events)
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    private static final int STRENGTH = 10;


    // Two constructors : one for character's relics and one for basic relics
    // Don't write the color parameter if it isn't a relic for a character : super(ID, NAME, RARITY, SOUND);

    // Base game hooks :
    // decompile AbstractCard

    // Special hooks :
    // https://github.com/daviscook477/BaseMod/wiki/Hooks
    // https://github.com/kiooeht/StSLib/wiki/Relic-Hooks
    public MyRelic() {
        super(ID, NAME, MyCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    // Just write "publi" and you'll see all the available hooks
    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        // Give the player the strength when he plays a card
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, STRENGTH)));
    }

    // Automatically updates the description in the json file
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STRENGTH + DESCRIPTIONS[1];
    }
}