import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Hangman extends PApplet {

Word word;
public void setup() {
  size(500, 500);
  word = new Word();
  word.selectRandomWord();
  word.makeCharArray();
  word.createLineObjects();
  textAlign(CENTER, CENTER);
  strokeCap(PROJECT);
}

public void draw() {
  background(210);
  word.selectKey();
  word.showLineObjects();
  word.showWrongCharacters();
  word.drawStickMan();
  //println(mouseY);
}

class Word {
  // String[] words = {"time", "year", "people", "way", "day", "man", "thing", "woman", "life", "child", "world", "school", "state", "family", "student", "group", "country", "problem", "hand", "part", "place", "case", "week", "company", "system", "program", "question", "work", "government", "number", "night", "point", "home", "water", "room", "mother", "area", "money", "story", "fact", "month", "lot", "right", "study", "book", "eye", "job", "word", "business", "issue", "side", "kind", "head", "house", "service", "friend", "father", "power", "hour", "game", "line", "end", "member", "law", "car", "city", "community", "name", "president", "team", "minute", "idea", "kid", "body", "information", "back", "parent", "face", "others", "level", "office", "door", "health", "person", "art", "war", "history", "party", "result", "change", "morning", "reason", "research", "girl", "guy", "moment", "air", "teacher", "force", "education" };
  String[] words = {"account", "achiever", "act", "action", "activity", "actor", "addition", "adjustment", "advertisement", "advice", "aftermath", "afternoon", "afterthought", "agreement", "air", "airplane", "airport", "alarm", "amount", "amusement", "anger", "angle", "animal" , "answer", "ant", "ants" , "apparatus", "apparel", "apple", "apples" , "appliance", "approval", "arch", "argument", "arithmetic", "arm", "army", "art", "attack", "attempt", "attention", "attraction", "aunt", "authority", "babies" , "baby" , "back", "badge", "bag", "bait", "balance", "ball", "balloon", "balls" , "banana", "band", "base", "baseball", "basin", "basket", "basketball", "bat", "bath", "battle", "bead", "beam", "bean", "bear", "bears" , "beast", "bed", "bedroom", "beds" , "bee", "beef", "beetle", "beggar", "beginner", "behavior", "belief", "believe", "bell", "bells" , "berry", "bike", "bikes" , "bird", "birds" , "birth", "birthday", "bit", "bite", "blade", "blood", "blow", "board", "boat", "boats" , "body", "bomb", "bone", "book", "books" , "boot", "border", "bottle", "boundary", "box", "boy", "boys" , "brain", "brake", "branch", "brass", "bread", "breakfast", "breath", "brick", "bridge", "brother", "brothers" , "brush", "bubble", "bucket", "building", "bulb", "bun", "burn", "burst", "bushes", "business", "butter", "button", "cabbage", "cable", "cactus", "cake", "cakes" , "calculator", "calendar", "camera", "camp", "can", "cannon", "canvas", "cap", "caption", "car" , "card", "care", "carpenter", "carriage", "cars" , "cart", "cast", "cat", "cats" , "cattle", "cause", "cave", "celery", "cellar", "cemetery", "cent", "chain", "chair" , "chairs" , "chalk", "chance", "change", "channel", "cheese", "cherries", "cherry", "chess", "chicken" , "chickens" , "children", "chin", "church", "circle", "clam", "class", "clock", "clocks" , "cloth", "cloud", "clouds" , "clover", "club", "coach", "coal", "coast", "coat", "cobweb", "coil", "collar", "color", "comb", "comfort", "committee", "company", "comparison", "competition", "condition", "connection", "control", "cook", "copper", "copy", "cord", "cork", "corn" , "cough", "country", "cover", "cow", "cows" , "crack", "cracker", "crate", "crayon", "cream", "creator", "creature", "credit", "crib", "crime", "crook", "crow", "crowd", "crown", "crush", "cry", "cub", "cup", "current", "curtain", "curve", "cushion", "dad", "daughter", "day", "death", "debt", "decision", "deer", "degree", "design", "desire", "desk", "destruction", "detail", "development", "digestion", "dime", "dinner", "dinosaurs" , "direction", "dirt", "discovery", "discussion", "disease", "disgust", "distance", "distribution", "division", "dock", "doctor", "dog", "dogs" , "doll", "dolls" , "donkey", "door", "downtown", "drain", "drawer", "dress", "drink", "driving", "drop", "drug", "drum", "duck" , "ducks" , "dust", "ear", "earth", "earthquake", "edge", "education", "effect", "egg", "eggnog", "eggs" , "elbow", "end", "engine", "error", "event", "example", "exchange", "existence", "expansion", "experience", "expert", "eye", "eyes", "face", "fact", "fairies" , "fall", "family", "fan", "fang" , "farm", "farmer" , "father", "father", "faucet", "fear", "feast", "feather", "feeling", "feet", "fiction", "field", "fifth", "fight", "finger", "finger", "fire" , "fireman", "fish", "flag", "flame", "flavor", "flesh", "flight", "flock", "floor", "flower", "flowers" , "fly", "fog", "fold", "food", "foot", "force", "fork", "form", "fowl", "frame", "friction", "friend", "friends" , "frog", "frogs" , "front", "fruit", "fuel", "furniture", "alley", "game", "garden", "gate", "geese", "ghost", "giants" , "giraffe", "girl", "girls" , "glass", "glove", "glue", "goat", "gold", "goldfish", "goodbye" , "goose", "government", "governor", "grade", "grain", "grandfather", "grandmother", "grape", "grass", "grip", "ground", "group", "growth", "guide", "guitar", "gun", "hair", "haircut", "hall", "hammer", "hand", "hands" , "harbor", "harmony", "hat", "hate", "head", "health", "hearing", "heart", "heat", "help", "hen", "hill" , "history", "hobbies", "hole", "holiday", "home" , "honey", "hook", "hope", "horn", "horse", "horses" , "hose", "hospital", "hot", "hour", "house", "houses" , "humor", "hydrant", "ice", "icicle", "idea", "impulse", "income", "increase", "industry", "ink", "insect", "instrument", "insurance", "interest", "invention", "iron", "island", "jail", "jam", "jar", "jeans", "jelly", "jellyfish", "jewel", "join", "joke", "journey", "judge", "juice", "jump", "kettle", "key", "kick", "kiss", "kite", "kitten", "kittens" , "kitty" , "knee", "knife", "knot", "knowledge", "laborer", "lace", "ladybug", "lake", "lamp", "land", "language", "laugh", "lawyer", "lead", "leaf", "learning", "leather", "leg" , "legs", "letter", "letters" , "lettuce", "level", "library", "lift", "light", "limit", "line", "linen", "lip", "liquid", "list", "lizards" , "loaf", "lock", "locket", "look", "loss", "love", "low", "lumber", "lunch", "lunchroom", "machine", "magic", "maid", "mailbox", "man", "manager", "map", "marble", "mark", "market", "mask", "mass", "match", "meal", "measure", "meat", "meeting", "memory", "men", "metal", "mice" , "middle", "milk", "mind", "mine", "minister", "mint", "minute", "mist", "mitten", "mom", "money", "monkey", "month", "moon", "morning", "mother", "motion", "mountain", "mouth", "move", "muscle", "music", "nail", "name", "nation", "neck", "need", "needle", "nerve", "nest" , "net", "news", "night", "noise", "north", "nose", "note", "notebook", "number", "nut", "oatmeal", "observation", "ocean", "offer", "office", "oil", "operation", "opinion", "orange", "oranges" , "order", "organization", "ornament", "oven", "owl", "owner", "page", "pail", "pain", "paint", "pan", "pancake", "paper", "parcel", "parent", "park", "part", "partner", "party", "passenger", "paste", "patch", "payment", "peace", "pear", "pen", "pencil", "person", "pest", "pet", "pets" , "pickle", "picture", "pie", "pies" , "pig", "pigs" , "pin", "pipe", "pizzas" , "place", "plane", "planes" , "plant", "plantation", "plants" , "plastic", "plate", "play", "playground", "pleasure", "plot", "plough", "pocket", "point", "poison", "police", "polish", "pollution", "popcorn", "porter", "position", "pot", "potato", "powder", "power", "price", "print", "prison", "process", "produce", "profit", "property", "prose", "protest", "pull", "pump", "punishment", "purpose", "push", "quarter", "quartz", "queen", "question", "quicksand", "quiet", "rabbit" , "rabbits" , "rail", "railway", "rain", "rainstorm", "rake", "range", "rat", "rate", "ray", "reaction", "reading", "reason", "receipt", "recess", "record", "regret", "relation", "religion", "representativ", "request", "respect", "rest", "reward", "rhythm", "rice", "riddle", "rifle", "ring", "rings" , "river", "road", "robin" , "rock", "rod", "roll", "roof", "room", "root", "rose", "route", "rub", "rule", "run", "sack", "sail", "salt", "sand", "scale", "scarecrow", "scarf", "scene", "scent", "school", "science", "scissors", "screw", "sea", "seashore", "seat", "secretary", "seed", "selection", "self", "sense", "servant", "shade", "shake", "shame", "shape", "sheep", "sheet", "shelf", "ship", "shirt", "shock", "shoe", "shoes" , "shop", "show", "side", "sidewalk", "sign", "silk", "silver", "sink", "sister", "sisters" , "size", "skate", "skin", "skirt", "sky", "slave", "sleep", "sleet", "slip", "slope", "smash", "smell", "smile", "smoke", "snail", "snails" , "snake", "snakes" , "sneeze", "snow", "soap", "society", "sock", "soda", "sofa", "son", "song", "songs" , "sort", "sound", "soup", "space", "spade", "spark", "spiders" , "sponge", "spoon", "spot", "spring", "spy", "square", "squirrel", "stage", "stamp", "star", "start", "statement", "station", "steam", "steel", "stem", "step", "stew", "stick", "sticks" , "stitch", "stocking", "stomach", "stone", "stop", "store", "story", "stove", "stranger", "straw", "stream", "street", "stretch", "string", "structure", "substance", "sugar", "suggestion", "suit", "summer", "sun", "support", "surprise", "sweater", "swim", "swing", "system", "table", "tail", "talk", "tank", "taste", "tax", "teaching", "team", "teeth" , "temper", "tendency", "tent", "territory", "test", "texture", "theory", "thing", "things" , "thought", "thread", "thrill", "throat", "throne", "thumb", "thunder", "ticket", "tiger", "time", "tin", "title", "toad", "toe", "toes", "tomatoes" , "tongue", "tooth", "toothbrush", "toothpaste", "top" , "touch", "town", "toy" , "toys" , "trade", "trail", "train", "trains" , "tramp", "transport", "tray", "treatment", "tree", "trees" , "trick", "trip", "trouble", "trousers", "truck", "trucks" , "tub", "turkey", "turn", "twig", "twist", "umbrella", "uncle", "underwear", "unit", "use", "vacation", "value", "van", "vase", "vegetable", "veil", "vein", "verse", "vessel", "vest", "view", "visitor", "voice", "volcano", "volleyball", "voyage", "walk", "wall", "war", "wash", "waste", "watch", "water", "wave", "waves" , "wax", "way", "wealth", "weather", "week", "weight", "wheel", "whip", "whistle", "wilderness", "wind" , "window" , "wine", "wing", "winter", "wire", "wish", "woman", "women", "wood", "wool", "word", "work", "worm", "wound", "wren", "wrench", "wrist", "writer", "writing", "yak", "yam", "yard", "yarn", "year", "yoke", "zebra", "zinc", "zipper", "zoo"};
  //String[] words = {"12345678901234567890"};
  String selectedWord;
  char[] chars;
  char previewKey;
  char previewKey2;
  char confirmedKey;
  int timer = 30;
  boolean showLine = false;
  Line[] lines;
  int x = width/2;
  int y = height/2+25;
  int lettersCorrect;
  int numLettersCorrect;
  boolean addCharacter;
  boolean win = false;
  boolean lose = false;
  ArrayList<Character> wrongLetters = new ArrayList<Character>();

  Word() {
  }

  public void selectRandomWord() {
    int rand = (int)(Math.random()*words.length);
    selectedWord = words[rand];
    //println(selectedWord);
  }

  public void makeCharArray() {
    chars = selectedWord.toCharArray();
    lines = new Line[chars.length];
  }

  public void createLineObjects() {
    for (int i = 0; i < chars.length; i ++) {
      int x = i * 40 + 40;
      lines[i] = new Line(chars[i], x, 175);
    }
  }

  public void showLineObjects() {
    for (int i = 0; i < lines.length; i ++) {
      lines[i].display();
    }
  }

  public void checkLineObjects() {
    numLettersCorrect = 0;
    addCharacter = false;
    for (int i = 0; i < lines.length; i ++) {
      if (lines[i].showBlank) {
        lines[i].check();
      }
    }

    for (int i = 0; i < chars.length; i ++) {
      if (chars[i] == confirmedKey) {
        addCharacter = true;
      }
    }

    if (!addCharacter) {
      boolean addChar = true;
      for (int i = 0; i < wrongLetters.size(); i ++) {
        Character chara = wrongLetters.get(i);
        if (chara == confirmedKey) {
          addChar = false;
        }
      }
      if (addChar) {
        wrongLetters.add(confirmedKey);
      }
      if (wrongLetters.size() >= 10) {
        lose = true;
        //println("LOSE");
      }
    }
    if (lettersCorrect == chars.length) {
      win = true;
    }
  }

  public void showWrongCharacters() {
    for (int i = 0; i < wrongLetters.size(); i ++) {
      int x = i*50+50;
      int y = height/2+125;
      if (i > 8) {
        y = height/2+175;
        x = (i-9)*50+50;
      }
      Character chara = wrongLetters.get(i);
      text(chara, x, y);
    }
  }

  public void drawStickMan () {
    strokeWeight(5);
    beginShape(LINES);
    if (wrongLetters.size() > 0) {
      vertex(180, 130);
      vertex(320, 130);
      if (wrongLetters.size() > 1) {
        vertex(230, 130);
        vertex(230, 20);
        if (wrongLetters.size() > 2) {
          vertex(230, 20);
          vertex(280, 20);
          if (wrongLetters.size() > 3) {
            vertex(280, 20);
            vertex(280, 40);
            if (wrongLetters.size() > 4) {
              ellipse(280, 55, 14, 14);
              if (wrongLetters.size() > 5) {
                vertex(280, 70);
                vertex(280, 100);
                if (wrongLetters.size() > 6) {
                  vertex(260, 90);
                  vertex(280, 80);
                  if (wrongLetters.size() > 7) {
                    vertex(280, 80);
                    vertex(300, 90);
                    if (wrongLetters.size() > 8) {
                      vertex(260, 120);
                      vertex(280, 100);
                      if (wrongLetters.size() > 9) {
                        vertex(280, 100);
                        vertex(300, 120);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    endShape();
  }

  public void selectKey() {
    if (!win && !lose) {
      if (keyPressed && key == ENTER) {
        confirmedKey = previewKey2;
        previewKey = ' ';
        checkLineObjects();
      } else {
        previewKey = key;
        previewKey2 = previewKey;
      }
      //println(previewKey);
      if (focused) {
      	textSize(50);
      	fill(255);
      	text(previewKey, x+80, y);
      	textSize(30);
      	fill(0);
      	text("press enter to select:", x-90, y+3);
  	  }
      if (frameCount % timer == 0) {
        if (showLine == false) {
          showLine = true;
        } else { 
          showLine = false;
        }
      }
      if (showLine && focused) {
        textSize(50);
        fill(0); 
        text("_", x+80, y+2);
      }
      if (!focused) {
      	textSize(30);
        fill(255, 0, 0);
        text("Click Here to Play", x, y+3);
      }
    } else {
      if (lose) {
        textSize(60);
        fill(200, 0, 0);
        text("YOU LOSE!!!", x, y-20);
        textSize(30);
        text("Refresh Page to Play Again", x, y+40);
        for (int i = 0; i < lines.length; i ++) {
          if (lines[i].showBlank) {
            lines[i].displayCharacter();
          }
        }
      } else {
        textSize(75);
        fill(0, 200, 0);
        text("YOU WIN!!!", x, y-20);
        textSize(30);
        text("Refresh Page to Play Again", x, y+40);
      }
    }
  }
}

class Line {
  char myChar;
  boolean showBlank = true;
  int x, y;
  int sizeOfText = 45;

  Line(char tempMyChar, int tempX, int tempY) {
    myChar = tempMyChar;  
    x = tempX;
    y = tempY;
  }

  public void display() {
    textSize(sizeOfText);
    if (showBlank) {
      fill(0);
      text("_", x, y+2);
    } else {
      fill(0);
      text(myChar, x, y+2);
    }
  }

  public void check() {
    if (word.confirmedKey == myChar) {
      showBlank = false;
      word.lettersCorrect++;
      word.numLettersCorrect++;
    }
  }

  public void displayCharacter() {
  	textSize(sizeOfText);
    fill(255);
    text(myChar, x, y+2);
    display();
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Hangman" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
