
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("ir"), QUIT("salir"), HELP("ayuda"), LOOK("mirar"), EAT("comer"), BACK("volver"), TAKE("coger"), DROP("tirar"), ITEMS("objetos"),
    UNKNOWN("desconocido");
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




