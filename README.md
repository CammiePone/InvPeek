<p align="center">
    <a href="https://modrinth.com/mod/qsl"><img src="https://raw.githubusercontent.com/intergrav/devins-badges/v3/assets/cozy/requires/quilt-standard-libraries_64h.png" width="311" height="64" title="Quilt Standard Libraries" alt="Quilt Standard Libraries"></a>
</p>

---

InvPeek adds HUD tooltips that let you preview small inventories without having to open them such as:
- Chiseled Bookshelves
- Lecterns
- Armor Stands
- Item Frames

---

<table align="center">
    <tr>
        <th><b>Join my Discord</b></th>
        <th><b>Join my Ko-Fi!</b></th>
    </tr>
    <tr>
        <th><a href="https://discord.gg/f5dFYWX"><img src="https://cammiescorner.dev/images/extras/discord.png" width="150" height="150" title="Cammie's Corner Discord" alt="Cammie's Corner Discord"></a></th>
        <th><a href="https://www.ko-fi.com/camellias_"><img src="https://cammiescorner.dev/images/extras/kofi.png" width="150" height="150" title="Cammie's Corner Ko-Fi" alt="Cammie's Corner Ko-Fi"></a></th>
    </tr>
</table>

---

## For Developers:

Step 1: You can also find the mod on Up's [maven repository](https://maven.uuid.gg/#/releases).

```gradle
repositories {
	maven { url = "https://maven.uuid.gg/releases" }
}

dependencies {
	modImplementation "dev.cammiescorner:InvPeek:<VERSION>"
}
```

Step 2: From there, you can create a new entrypoint
```java
public class ExampleEntrypoint implements InvPeekInitializer {
	@Override
	public void init(InvPeekRegistry registry) {
		// block entity example
		registry.registerBlockEntity(BlockEntityType.LECTERN, (player, world, hitResult, blockState, lectern) -> lectern.getBook());
		
		// entity example
		registry.registerEntity(EntityType.ITEM_FRAME, (player, world, hitResult, itemFrame) -> itemFrame.getHeldItemStack());
	}
}
```

Step 3: Then add it to the `entrypoints` block in your `quilt.mod.json`
```json
"entrypoints": {
	"invpeek": "com.example.examplemod.ExampleEntrypoint"
}
```

Step 4: Profit???
