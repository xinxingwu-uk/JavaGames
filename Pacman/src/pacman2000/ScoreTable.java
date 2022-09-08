package pacman2000;
// Download by http://www.codefans.net
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class ScoreTable
  implements Iterable<Score>
{
  private int size;
  private Vector<Score> scores = new Vector();
  private String filename;
  private ScoreComparator scoreComparator = new ScoreComparator();

  public ScoreTable(String filename, int size)
  {
    this.filename = filename;
    this.size = size;

    fix();
  }

  public void addScore(String name, int value)
  {
    addScore(new Score(name, value));
  }

  public void addScore(Score score)
  {
    if (!isScoreGoodEnough(score.getValue())) return;

    this.scores.add(score);
    sort();
    fix();
  }

  public boolean isScoreGoodEnough(int value)
  {
    if (this.scores.size() < this.size) return true;

    Score worst = (Score)this.scores.get(this.scores.size() - 1);

    return value > worst.getValue();
  }

  public Score getScore(int index)
  {
    return (Score)this.scores.get(index);
  }

  public void empty()
  {
    this.scores.clear();
  }

  public void reset()
  {
    empty();
    fix();
  }

  public void fix()
  {
    while (this.scores.size() < this.size) {
      addScore(new Score("No name", 0));
    }

    while (this.scores.size() > this.size) {
      this.scores.remove(this.scores.size() - 1);
    }

    sort();
  }

  public void sort()
  {
    Collections.sort(this.scores, this.scoreComparator);
    Collections.reverse(this.scores);
  }

  public void load()
  {
    System.out.println("Loading highscores");
    empty();

    BufferedReader in = null;
    try
    {
      in = new BufferedReader(new FileReader(this.filename));
      String temp;
      while ((temp = in.readLine()) != null) {
        int endVal = temp.indexOf("...");
        int begName = endVal + 3;

        String tempVal = temp.substring(0, endVal);
        String tempName = temp.substring(begName, temp.length());

        Score score = new Score(tempName, new Integer(tempVal).intValue());
        addScore(score);
      }
    }
    catch (Exception ex) {
      System.out.println("Could not load highscores from: " + this.filename);
    }
    finally {
      if (in != null)
        try {
          in.close();
        }
        catch (IOException ex)
        {
        }
    }
    fix();
  }

  public void save()
  {
    System.out.println("Saving highscores");

    FileWriter out = null;
    try
    {
      out = new FileWriter(this.filename);

      for (Score score : this.scores) {
        String temp = score.getValue() + "..." + score.getName() + "\n";
        out.write(temp);
      }
    }
    catch (IOException ex) {
      System.out.println("Could not write highscores to: " + this.filename);
    }
    finally {
      if (out != null)
        try {
          out.close();
        }
        catch (IOException ex)
        {
        }
    }
  }

  public String getDisplayString() {
    String text = "";

    int current = 0;
    for (Score score : this.scores) {
      current++;
      text = text + "      ";

      switch (current) {
      case 1:
        text = text + "1st";
        break;
      case 2:
        text = text + "2nd";
        break;
      case 3:
        text = text + "3rd";
        break;
      default:
        text = text + current + "th";
      }

      text = text + ": ";
      text = text + score.getName();
      text = text + " with ";
      text = text + score.getValue();
      text = text + " points ";
    }

    return text;
  }

  public Iterator<Score> iterator()
  {
    return this.scores.iterator();
  }
}