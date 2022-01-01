import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Smartphone {
    String name;
    String brand;
    LocalDate releaseDate;
    Double weight;
    Integer resolutionX;
    Integer resolutionY;
    Double depth;
    Double width;
    Double height;
    Integer storage;
    Integer RAM;
    String chipset;
    String color;

    public Smartphone(String smartphone) throws FileNotFoundException
    {
        File parent = new File(System.getProperty("user.dir"));
        File phoneFile = new File(parent.getAbsolutePath() + "\\Smartphones\\" + smartphone + ".txt");
        Scanner phoneScanner = new Scanner(phoneFile);
        setSpecs(phoneScanner);
    }

    // getters
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public Double getWeight() { return weight; }
    public Integer getResolutionX() { return resolutionX; }
    public Integer getResolutionY() { return resolutionY; }
    public Double getDepth() { return depth; }
    public Double getWidth() { return width; }
    public Double getHeight() { return height; }
    public Integer getStorage() { return storage; }
    public Integer getRAM() { return RAM; }
    public String getChipset() { return chipset; }
    public String getColor() { return color; }

    private void setSpecs(Scanner phoneScanner)
    {
        while(phoneScanner.hasNextLine())
        {
            String line = phoneScanner.nextLine();
            String header = line.substring(0,line.indexOf(':'));
            String content = line.substring(line.indexOf(':') + 1);
            switch (header) {
                case "name" -> name = content;
                case "brand" -> brand = content;
                case "releaseDate"-> releaseDate = LocalDate.parse(content);
                case "weight" -> weight = Double.parseDouble(content);
                case "resolution" -> {
                    resolutionX = Integer.parseInt(content.substring(0,content.indexOf('x')));
                    resolutionY = Integer.parseInt(content.substring(content.indexOf('x') + 1));
                }
                case "dimensions" -> {
                    height = Double.parseDouble(content.substring(0,content.indexOf('x')));
                    width = Double.parseDouble(content.substring(content.indexOf('x') + 1, content.lastIndexOf('x')));
                    depth = Double.parseDouble(content.substring(content.lastIndexOf('x') + 1));
                }
                case "chipset" -> chipset = content;
                case "storage" -> storage = Integer.parseInt(content);
                case "RAM" -> RAM = Integer.parseInt(content);
                case "color" -> color = content;
            }
        }
    }

    @Override
    public String toString() {
        return  name + "\n" +
                brand + "\n" +
                releaseDate + "\n" +
                weight + "\n" +
                resolutionX + "\n" +
                resolutionY + "\n" +
                height + "\n" +
                width + "\n" +
                depth + "\n" +
                storage + "\n" +
                RAM + "\n" +
                chipset + "\n" +
                color;
    }
}
