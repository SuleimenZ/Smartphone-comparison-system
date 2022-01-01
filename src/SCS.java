import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SCS {
    private String directory;
    List<Smartphone> smartphones;

    private Smartphone firstSelected;
    private Smartphone secondSelected;

    private int tableCellWidth;
    private int tableCellHeight;

    public SCS(String directory) {
        this.directory = directory;
        updateSmartphoneList();
        setTableCellParameters(25,4);
    }

    public void Run() {
        Scanner scan = new Scanner(System.in);
        String input;
        int intInput = 1;
        boolean parsed = false;
        int smartphonesSelected = 0;

        while(smartphonesSelected != 2){    //smartphone selection section
            switch (smartphonesSelected){
                case 0 -> {                 //first phone selection
                    printSmartphoneList();
                    while(!parsed){
                        try {
                            System.out.print("Choose first smartphone: ");
                            input = scan.nextLine();
                            intInput = Integer.parseInt(input);
                            parsed = true;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\r"); //crappy console clear
                        } catch (NumberFormatException e) { continue; }

                        if(intInput <= 0 || intInput > smartphones.size() ) { parsed = false; }
                    }
                    firstSelected = smartphones.get(intInput - 1);

                    smartphonesSelected++;
                    parsed = false;
                }
                case 1 -> {                 //second phone selection
                    printSmartphoneList();
                    while(!parsed){
                        try {
                            System.out.print("Choose second smartphone: ");
                            input = scan.nextLine();
                            intInput = Integer.parseInt(input);
                            parsed = true;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\r"); //crappy console clear
                        } catch (NumberFormatException e) { continue; }

                        if(intInput <= 0 || intInput > smartphones.size() ) { parsed = false; }
                    }

                    if(intInput == smartphones.indexOf(firstSelected) + 1) {    //If user selects the same phone, it will be unselected
                        firstSelected = null;
                        smartphonesSelected--;
                        parsed = false;
                        continue;
                    }
                    secondSelected = smartphones.get(intInput - 1);
                    smartphonesSelected++;
                }
            }
        }

        printComparisonTable(firstSelected, secondSelected);
    }

    private void printComparisonTable(Smartphone firstSelected, Smartphone secondSelected) {
        int smartphones = 2;
        //ensuring both values are enough to not cause different exceptions while printing
        ensureTableCellParameters();

        //printing table
        for (int r = 0; r < 11;r++){
            for (int h = 0; h < tableCellHeight; h++) {
                if(h% tableCellHeight == 0){
                    System.out.print("+");
                }
                else {
                    System.out.print("|");
                }
                for(int i = 0; i < smartphones + 1; i++) {
                    if(h% tableCellHeight == 0){
                        System.out.print(new String(new char[tableCellWidth -1]).replace("\0", "-") + "+");
                    }
                    else if(h == Math.ceil((float) tableCellHeight /2)){
                        if(i == 0){
                            switch (r){
                                case 0 ->
                                        System.out.print("Name" + new String(new char[tableCellWidth -4-1]).replace("\0", " ") + "|");
                                case 1 ->
                                        System.out.print("Brand" + new String(new char[tableCellWidth -5-1]).replace("\0", " ") + "|");
                                case 2 ->
                                        System.out.print("Color" + new String(new char[tableCellWidth -5-1]).replace("\0", " ") + "|");
                                case 3 ->
                                        System.out.print("Storage" + new String(new char[tableCellWidth -7-1]).replace("\0", " ") + "|");
                                case 4 ->
                                        System.out.print("RAM" + new String(new char[tableCellWidth -3-1]).replace("\0", " ") + "|");
                                case 5 ->
                                        System.out.print("Battery" + new String(new char[tableCellWidth -7-1]).replace("\0", " ") + "|");
                                case 6 ->
                                        System.out.print("Chipset" + new String(new char[tableCellWidth -7-1]).replace("\0", " ") + "|");
                                case 7 ->
                                        System.out.print("Resolution" + new String(new char[tableCellWidth -10-1]).replace("\0", " ") + "|");
                                case 8 ->
                                        System.out.print("Dimensions" + new String(new char[tableCellWidth -10-1]).replace("\0", " ") + "|");
                                case 9 ->
                                        System.out.print("Weight" + new String(new char[tableCellWidth -6-1]).replace("\0", " ") + "|");
                                case 10 ->
                                        System.out.print("Release" + new String(new char[tableCellWidth -7-1]).replace("\0", " ") + "|");
                                default ->
                                        System.out.print(new String(new char[tableCellWidth -1]).replace("\0", " ") + "|");
                            }
                        }
                        else if(i == 1){
                            getSpecificSmartphoneInfo(firstSelected, r, tableCellWidth);
                        }
                        else {
                            getSpecificSmartphoneInfo(secondSelected, r, tableCellWidth);
                        }
                    }
                    else{
                        System.out.print(new String(new char[tableCellWidth -1]).replace("\0", " ") + "|");
                    }
                }
                System.out.println();
            }
        }
        //printing last line
        System.out.print("+");
        for(int i = 0; i < smartphones + 1; i++) {
            System.out.print(new String(new char[tableCellWidth -1]).replace("\0", "-") + "+");
        }
    }
    //this method is created to improve printComparisonSheet readability
    private void getSpecificSmartphoneInfo(Smartphone selectedSmartphone, int row, int generalWidth) {
        switch (row){
            case 0 -> {
                String content = selectedSmartphone.getName();
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 1 -> {
                String content = selectedSmartphone.getBrand();
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 2 -> {
                String content = selectedSmartphone.getColor();
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 3 -> {
                String content = selectedSmartphone.getStorage().toString() + " GB";
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 4 -> {
                String content = selectedSmartphone.getRAM().toString() + " GB";
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 5 -> {
                String content = selectedSmartphone.getBattery().toString() + "mAh";
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 6 -> {
                String content = selectedSmartphone.getChipset();
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 7 -> {
                String content = selectedSmartphone.getResolutionX() + "x" + selectedSmartphone.getResolutionY() + "px";
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 8 -> {
                String content = selectedSmartphone.getWidth() + "x" + selectedSmartphone.getHeight() + "x" + selectedSmartphone.getDepth() + "mm";
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 9 -> {
                String content = selectedSmartphone.getWeight().toString() + "g";
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            case 10 -> {
                String content = selectedSmartphone.getReleaseDate().toString();
                int spaces = generalWidth - content.length();
                System.out.print(content + new String(new char[spaces-1]).replace("\0", " ") + "|");
            }
            default ->
                    System.out.print(new String(new char[generalWidth-1]).replace("\0", " ") + "|");
        }
    }

    public void printSmartphoneList(){
        List<Smartphone> smartphones = getSmartphoneList(directory);
        if(smartphones.size() == 0) {
            System.out.println("No smartphones were found");
        }

        for (int i = 0; i < smartphones.size(); i++){
            System.out.print(i+1 + ". " + smartphones.get(i).getName());
            if(smartphones.get(i).equals(firstSelected) || smartphones.get(i).equals(secondSelected) ){
                System.out.print("[✓]");
            }
            System.out.println();
        }
    }

    public List<Smartphone> getSmartphoneList(String directory) {
        File folder = new File(directory);
        File[] smartphonePaths = folder.listFiles();
        List<Smartphone> smartphones = new ArrayList<>();

        assert smartphonePaths != null;
        for (File path : smartphonePaths) {
            Smartphone sp;
            try {
                sp = new Smartphone(path.getAbsolutePath());
            }catch (FileNotFoundException e){
                continue;
            }

            if(sp.getName() == null) { continue; }
            smartphones.add(sp);
        }
        return smartphones;
    }

    public void changeDirectory(String directory){
        this.directory = directory;
        updateSmartphoneList();
    }
    public void setTableCellParameters(int width, int height){
        tableCellWidth = width;
        tableCellHeight = height;
    }
    private void ensureTableCellParameters(){
        tableCellWidth = Math.max(tableCellWidth, Math.max(firstSelected.getMaxStringLength(), secondSelected.getMaxStringLength()));
        tableCellHeight = Math.max(tableCellHeight, 2);
    }
    private void updateSmartphoneList(){
        smartphones = getSmartphoneList(directory);
    }
}
