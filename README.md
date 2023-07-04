# GUILib

<t>Библиотека для быстрого создания меню.<br>
<t>Поддерживает Java 16+, Spigot 1.16+<t>

![Screenshot](https://github.com/devoirr/GUILib/assets/138160506/bb90b9f9-7a2a-4809-b1d0-460884b0b9a9)


## Добавление в проект
**Gradle**
``` gradle
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.devoirr:GUILib:1.0.1'
}
```
**Maven**
``` xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>

<dependency>
  <groupId>com.github.devoirr</groupId>
  <artifactId>GUILib</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Использование
``` java
public class ExamplePanel extends Panel {

    public ExamplePanel(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getTitle() {
        return "Example menu";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void setItems() {

        inventory.setItem(3, new ItemStack(Material.COMPASS));
        inventory.setItem(5, new ItemStack(Material.BONE));

        fillEmpty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

    }

    @Click(slots = 3)
    public void onCompass(InventoryClickEvent event) {
        player.sendMessage("Вы нажали на компасс.");
    }

    @Click(slots = 5)
    public void onBone(InventoryClickEvent event) {
        player.sendMessage("Вы нажали на кость.");
        player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1f, 1f);
    }

    @Close
    public void onClose(InventoryCloseEvent event) {
        player.sendMessage("Вы закрыли меню.");
    }

}

```
__Обратите внимание на аннотации:__ </p>
**@Click** - Данная аннотация позволяет указать метод который будет обрабатывать
события клика. Если вы хотите чтобы метод обрабатывал только конкретные слоты,
укажите их в скобках: 
``` java 
@Click(slots = { 1,2,3 })
```
Метод обработки обязательно должен быть публичным, но их может быть сколько угодно.
Просто создайте новый публичный метод принимающий InventoryClickEvent с аннотацией **@Click**.

**@Close** - Указывает на метод обработки закрытия инвентаря. Их также может быть сколько угодно,
просто создайте метод принимающий InventoryCloseEvent с аннотацией @Close.

Чтобы открыть меню используйте метод open:
``` java
Player player = ...
new ExamplePanel(plugin).open(player);
```
<t></t>
