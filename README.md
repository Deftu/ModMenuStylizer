# Mod Menu Stylizer
Allows you to customize Mod Menu mod icons and badges.

---

[![Discord Badge](https://raw.githubusercontent.com/intergrav/devins-badges/v2/assets/cozy/social/discord-singular_64h.png)](https://s.deftu.dev/discord)
[![Ko-Fi Badge](https://raw.githubusercontent.com/intergrav/devins-badges/v2/assets/cozy/donate/kofi-singular_64h.png)](https://s.deftu.dev/kofi)

---

## How do I set it up?

### Replacing a mod's icon

Ensure that your new icon is square-shaped and is a PNG file.  
Replacing a mod's icon is as simple as placing the file in `Deftu/ModMenuStylizer` and renaming it to the mod's ID.

For example, if I wanted to replace the icon for the mod `craftpresence` with my image called `icon.png`, I would simply put `icon.png` in `Deftu/ModMenuStylizer` and rename it to `craftpresence.png`.

### Adding a badge to a mod

Create a JSON file in `Deftu/ModMenuStylizer` with the name of the mod's ID. (f.ex, `craftpresence.json`)
The JSON file should look like this:
```json
{
    "badges": [
        {
            "text": "Cool",
            "color": {
                "red": 255,
                "green": 0,
                "blue": 0
            },
            "outline_color": {
                "red": 0,
                "green": 0,
                "blue": 0
            }
        }
    ]
}
```
The `outline_color` field is optional, and will default to `color` if not specified.  
The color fields can either be objects containing `red`, `green` and `blue` fields, or a RGB integer.

---

[![BisectHosting](https://www.bisecthosting.com/partners/custom-banners/8fb6621b-811a-473b-9087-c8c42b50e74c.png)](https://bisecthosting.com/deftu)

---

**This project is licensed under [LGPL-3.0][lgpl3].**\
**&copy; 2024 Deftu**

[lgpl3]: https://www.gnu.org/licenses/lgpl-3.0.en.html
