
public interface UserInterface {
    
    boolean nextBoolean();

    
    String nextLine();
    
   
    void print(String message);
    
   
    void println(String message);

   
    final String PLAY_AGAIN_MESSAGE = "Challenge me again?";
    final String SAVE_MESSAGE = "Shall I remember these games?";
    final String LOAD_MESSAGE = "Shall I recall our previous games?";
    final String SAVE_LOAD_FILENAME_MESSAGE = "What is the file name?";
    final String STATUS_MESSAGE = "Games played: %d\nI won: %d";
    final String BANNER_MESSAGE = "Think of an item, and I will guess it.";
}