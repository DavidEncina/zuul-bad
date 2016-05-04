
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("andare"), QUIT("smettere"), HELP("aiuto"), LOOK("guarda"), EAT("mangiare"), BACK("indietro"), TAKE("prendere"), DROP("buttare"), ITEMS("elementi"),
    UNKNOWN("sconosciuto");
    private String command;
    
    /**
     * Constructor de la clase Option
     */
    private Option(String command)
    {
        this.command = command;
    }
    
    public String getCommand()
    {
        return command;
    }
}




