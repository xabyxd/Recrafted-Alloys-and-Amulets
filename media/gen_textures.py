#!/usr/bin/env python3
"""
Genera texturas de bloques y menas (16x16) recoloreando una plantilla.

Uso:
    pip install pillow
    python gen_textures.py

Pon template_block.png y copper_ore.png junto al script (o cambia las rutas).
Las texturas salen en ./out/
"""
from pathlib import Path
from PIL import Image

BLOCK_TEMPLATE = "template_block.png"
ORE_TEMPLATE = "copper_ore.png"
# prefijo de salida -> (plantilla, contraste). Plantillas grises con transparencia.
ITEM_TEMPLATES = {
    "ingot":  ("ingot.png", 1.2),
    "nugget": ("nugget.png", 1.2),
    "dust":   ("template_dust.png", 1.6),
}
ITEM_METALS = ["lead", "silver", "tin", "copper", "bronze", "steel"]
OUT_DIR = Path("out")

# nombre -> hex del color base
BLOCKS = {
    "lead_block":   "65556a",
    "silver_block": "b3b2bf",
    "tin_block":    "909090",
    "copper_block": "ab4f0f",
    "bronze_block": "b56724",
    "steel_block":  "868686",
}

# nombre -> (hex, multiplicador de brillo extra para las vetas)
ORES = {
    "lead_ore":   ("65556a", 1.0),
    "tin_ore":    ("909090", 1.22),  # aclarado para que se distinga de la piedra
    "copper_ore": ("ab4f0f", 1.0),
    "silver_ore": ("b3b2bf", 1.0),
}

BLOCK_CONTRAST = 1.4   # cuanto se exagera la variacion de la plantilla
ORE_CONTRAST = 2.6
ORE_REF = (0xC1, 0x6E, 0x36)    # tono medio de la veta de cobre de la plantilla
BLOCK_REF = (0xDA, 0xE9, 0xF3)  # tono dominante de la plantilla de bloque


def hex_to_rgb(h):
    return tuple(int(h[i:i + 2], 16) for i in (0, 2, 4))


def lum(p):
    return 0.299 * p[0] + 0.587 * p[1] + 0.114 * p[2]


def scale(color, ratio):
    return tuple(max(0, min(255, round(c * ratio))) for c in color) + (255,)


def make_block(template, hex_color):
    """Cada pixel se ilumina/oscurece como en la plantilla, pero con el color base."""
    base, ref = hex_to_rgb(hex_color), lum(BLOCK_REF)
    out = Image.new("RGBA", template.size)
    for y in range(template.height):
        for x in range(template.width):
            ratio = 1 + (lum(template.getpixel((x, y))) / ref - 1) * BLOCK_CONTRAST
            out.putpixel((x, y), scale(base, ratio))
    return out


def make_ore(template, hex_color, lift=1.0):
    """Deja la piedra intacta y recolorea solo los pixeles de veta (los naranjas)."""
    base, ref = hex_to_rgb(hex_color), lum(ORE_REF)
    out = template.copy()
    for y in range(template.height):
        for x in range(template.width):
            p = template.getpixel((x, y))
            if p[0] - p[2] > 25:  # pixel de veta = rojo claramente mayor que azul
                ratio = (1 + (lum(p) / ref - 1) * ORE_CONTRAST) * lift
                out.putpixel((x, y), scale(base, ratio))
    return out


def make_item(template, hex_color, contrast):
    """Plantilla gris con alpha: el brillo medio pasa a ser el color base,
    lo oscuro se oscurece y lo claro se mezcla hacia blanco (sin quemar el tono)."""
    base = hex_to_rgb(hex_color)
    px = [p for p in template.getdata() if p[3] > 0]
    ref = sum(lum(p) for p in px) / len(px)
    out = Image.new("RGBA", template.size, (0, 0, 0, 0))
    for y in range(template.height):
        for x in range(template.width):
            p = template.getpixel((x, y))
            if p[3] == 0:
                continue
            d = (lum(p) / ref - 1) * contrast
            if d < 0:
                c = tuple(round(v * (1 + d)) for v in base)
            else:
                c = tuple(round(v + (255 - v) * d * 0.5) for v in base)
            out.putpixel((x, y), tuple(max(0, min(255, v)) for v in c) + (p[3],))
    return out


def main():
    OUT_DIR.mkdir(exist_ok=True)
    block_t = Image.open(BLOCK_TEMPLATE).convert("RGBA")
    ore_t = Image.open(ORE_TEMPLATE).convert("RGBA")

    for name, h in BLOCKS.items():
        make_block(block_t, h).save(OUT_DIR / f"{name}.png")
    for name, (h, lift) in ORES.items():
        make_ore(ore_t, h, lift).save(OUT_DIR / f"{name}.png")

    hexes = {n.replace("_block", ""): h for n, h in BLOCKS.items()}
    total = len(BLOCKS) + len(ORES)
    for prefix, (path, contrast) in ITEM_TEMPLATES.items():
        t = Image.open(path).convert("RGBA")
        for metal in ITEM_METALS:
            make_item(t, hexes[metal], contrast).save(OUT_DIR / f"{metal}_{prefix}.png")
            total += 1

    print(f"Listo: {total} texturas en {OUT_DIR}/")


if __name__ == "__main__":
    main()
