Word word;
void setup() {
  size(500, 500);
  word = new Word();
  word.selectRandomWord();
  word.makeCharArray();
  word.createLineObjects();
  textAlign(CENTER, CENTER);
}

void draw() {
  background(210);
  word.selectKey();
  word.showLineObjects();
  word.showWrongCharacters();
  word.drawStickMan();
  //println(mouseY);
}

class Word {
  String[] words = {"time", "year", "people", "way", "day", "man", "thing", "woman", "life", "child", "world", "school", "state", "family", "student", "group", "country", "problem", "hand", "part", "place", "case", "week", "company", "system", "program", "question", "work", "government", "number", "night", "point", "home", "water", "room", "mother", "area", "money", "story", "fact", "month", "lot", "right", "study", "book", "eye", "job", "word", "business", "issue", "side", "kind", "head", "house", "service", "friend", "father", "power", "hour", "game", "line", "end", "member", "law", "car", "city", "community", "name", "president", "team", "minute", "idea", "kid", "body", "information", "back", "parent", "face", "others", "level", "office", "door", "health", "person", "art", "war", "history", "party", "result", "change", "morning", "reason", "research", "girl", "guy", "moment", "air", "teacher", "force", "education" };
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

  void selectRandomWord() {
    int rand = (int)(Math.random()*words.length);
    selectedWord = words[rand];
    //println(selectedWord);
  }

  void makeCharArray() {
    chars = selectedWord.toCharArray();
    lines = new Line[chars.length];
  }

  void createLineObjects() {
    for (int i = 0; i < chars.length; i ++) {
      int x = i * 50 + 50;
      lines[i] = new Line(chars[i], x, 175);
    }
  }

  void showLineObjects() {
    for (int i = 0; i < lines.length; i ++) {
      lines[i].display();
    }
  }

  void checkLineObjects() {
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

  void showWrongCharacters() {
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

  void drawStickMan () {
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

  void selectKey() {
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
      textSize(50);
      fill(255);
      text(previewKey, x+80, y);
      textSize(30);
      fill(0);
      if (focused) 
      text("press enter to select:", x-90, y+3);
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
        fill(255, 0, 0);
        text("YOU LOSE!!!", x, y-20);
        textSize(30);
        fill(255, 0, 0);
        text("Refresh Page to Play Again", x, y+40);
        for (int i = 0; i < lines.length; i ++) {
          if (lines[i].showBlank) {
            lines[i].displayCharacter();
          }
        }
      } else {
        textSize(75);
        fill(0, 255, 0);
        text("YOU WIN!!!", x, y-20);
      }
    }
  }
}

class Line {
  char myChar;
  boolean showBlank = true;
  int x, y;
  int length = 5;

  Line(char tempMyChar, int tempX, int tempY) {
    myChar = tempMyChar;  
    x = tempX;
    y = tempY;
  }

  void display() {
    textSize(45);
    if (showBlank) {
      fill(0);
      text("_", x, y+2);
    } else {
      fill(0);
      text(myChar, x, y+2);
    }
  }

  void check() {
    if (word.confirmedKey == myChar) {
      showBlank = false;
      word.lettersCorrect++;
      word.numLettersCorrect++;
    }
  }

  void displayCharacter() {
  	textSize(45);
    fill(255);
    text(myChar, x, y+2);
    display();
  }
}
