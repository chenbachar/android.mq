package app.cbdev.motivationquotes;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private ArrayList<String> motivational_quotes, love_quotes;
    private String urlMotivation = "https://raw.githubusercontent.com/chenbachar/android.mq/master/motivationquotes.txt";
    private String urlLove = "https://raw.githubusercontent.com/chenbachar/android.mq/master/lovequotes.txt";
    private String currentSection;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        motivational_quotes = new ArrayList<>();
        love_quotes = new ArrayList<>();
        initializeQuotes();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        readQuotes(urlMotivation, "motivation");
        readQuotes(urlLove, "love");

        // Set up the drawer.
        mNavigationDrawerFragment.setUp
                (R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        Fragment objFragment = null;
        switch (position) {
            case 0:
                mTitle = getString(R.string.title_sectionMotivationQuotes);
                currentSection = "motivation";
                break;
            case 1:
                currentSection = "love";
                mTitle = getString(R.string.title_sectionLoveQuotes);
                break;
        }
        objFragment = new Quote();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                break;
            case 2:
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void readQuotes(String url, String type) {
        DownloadTextTask task;
        try {
            switch (type) {
                case "motivation":
                    task = new DownloadTextTask(motivational_quotes);
                    task.execute(url);
                    motivational_quotes = task.get();
                    break;
                case "love":
                    task = new DownloadTextTask(love_quotes);
                    task.execute(url);
                    love_quotes = task.get();
                    break;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getQuotes() {
        switch (currentSection) {
            case "motivation":
                return motivational_quotes;
            case "love":
                return love_quotes;
        }
        return new ArrayList<>();
    }

    private void initializeQuotes() {
        initializeMotivationQuote();
        initializeLoveQuote();
    }

    private void initializeMotivationQuote() {
        String[] s = {
                "Failure will never overtake me if my determination to succeed is strong enough.#-Og Mandino",
                "What you do today can improve all your tomorrows.#-Ralph Marston",
                "In order to succeed, we must first believe that we can.#-Nikos Kazantzakis",
                "Aim for the moon. Even if you miss, you will land among the stars.#-W.Clement Stone",
                "A creative man is motivated by the desire to achieve, not by the desire to beat others.#-Ayn Rand",
                "Always do your best. What you plant now, you will harvest later.#-Og Mandino",
                "Don't watch the clock; do what it does. Keep going.#-Sam Levenson",
                "The will to win, the desire to succeed, the urge to reach your full potential; these are the keys that will unlock the door to personal excellence.#-Confucius",
                "It does not matter how slowly you go as long as you do not stop.#-Confucius",
                "You are never too old to set another goal or to dream a new dream.#-C. S. Lewis",
                "With the new day comes new strength and new thoughts.#-Eleanor Roosevelt",
                "If you can dream it, you can do it.#-Walt Disney",
                "The secret of getting ahead is getting started.#-Mark Twain",
                "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.#-Thomas A. Edison",
                "Infuse your life with action. Don't wait for it to happen. Make it happen. Make your own future. Make your own hope. Make your own love. And whatever your beliefs, honor your creator, not by passively waiting for grace to come down from upon high, but by doing what you can to make grace happen... yourself, right now, right down here on Earth.#-Bradley Whitford",
                "Expect problems and eat them for breakfast.#-Alfred A. Montapert",
                "Your talent is God's gift to you. What you do with it is your gift back to God.#-Leo Buscaglia",
                "Keep your eyes on the stars, and your feet on the ground.#-Theodore Roosevelt",
                "Believe in yourself! Have faith in your abilities! Without a humble but reasonable confidence in your own powers you cannot be successful or happy.#-Norman Vincent Peale",
                "You have to learn the rules of the game. And then you have to play better than anyone else.#-Albert Einstein",
                "We may encounter many defeats but we must not be defeated.#-Maya Angelou",
                "Problems are not stop signs, they are guidelines.#-Robert H. Schuller",
                "Start where you are. Use what you have. Do what you can.#-Arthur Ashe",
                "There is only one corner of the universe you can be certain of improving, and that's your own self.#-Aldous Huxley",
                "Optimism is the faith that leads to achievement. Nothing can be done without hope and confidence.#-Helen Keller"
        };

        for (int i = 0; i < s.length; i++) {
            motivational_quotes.add(s[i]);
        }

    }

    private void initializeLoveQuote() {
        String[] s = {
                "Love isn't something you find. Love is something that finds you.#-Loretta Young",
                "Let us always meet each other with smile, for the smile is the beginning of love.#-Mother Teresa",
                "True love is like ghosts, which everyone talks about and few have seen.#-Francois de La Rochefoucauld",
                "Immature love says: 'I love you because I need you.' Mature love says 'I need you because I love you.'#-Erich Fromm",
                "Sometimes the heart sees what is invisible to the eye.#-H. Jackson Brown, Jr.",
                "A flower cannot blossom without sunshine, and man cannot live without love.#-Max Muller",
                "The best thing to hold onto in life is each other.#-Audrey Hepburn",
                "Keep love in your heart. A life without it is like a sunless garden when the flowers are dead.#-Oscar Wilde",
                "The sweetest of all sounds is that of the voice of the woman we love.#-Jean de la Bruyere",
                "I have found the paradox, that if you love until it hurts, there can be no more hurt, only more love.#-Mother Teresa",
                "Spread love everywhere you go. Let no one ever come to you without leaving happier.#-Mother Teresa",
                "Love is life. And if you miss love, you miss life.#-Leo Buscaglia",
                "Love doesn't make the world go around. Love is what makes the ride worthwhile.#-Franklin P. Jones",
                "Being deeply loved by someone gives you strength, while loving someone deeply gives you courage.#-Lao Tzu",
                "Love is when the other person's happiness is more important than your own.#-H. Jackson Brown, Jr.",
                "A kiss is a lovely trick designed by nature to stop speech when words become superfluous.#-Ingrid Bergman",
                "We love life, not because we are used to living but because we are used to loving.#-Friedrich Nietzsche",
                "We're born alone, we live alone, we die alone. Only through our love and friendship can we create the illusion for the moment that we're not alone.#-Orson Welles",
                "Love is composed of a single soul inhabiting two bodies.#-Aristotle",
                "You can search throughout the entire universe for someone who is more deserving of your love and affection than you are yourself, and that person is not to be found anywhere. You yourself, as much as anybody in the entire universe deserve your love and affection.#-Buddha",
                "Love is like a friendship caught on fire. In the beginning a flame, very pretty, often hot and fierce, but still only light and flickering. As love grows older, our hearts mature and our love becomes as coals, deep-burning and unquenchable.#-Bruce Lee",
                "We loved with a love that was more than love.#-Edgar Allan Poe",
                "We waste time looking for the perfect lover, instead of creating the perfect love.#-Tom Robbins"
        };

        for (int i = 0; i < s.length; i++) {
            love_quotes.add(s[i]);
        }
    }

}
