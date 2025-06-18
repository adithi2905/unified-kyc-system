from pathlib import Path
import re
import sys
import cv2
import easyocr

def findSSN(text):
    text = text.replace(' ', '').replace('*', '-').replace('–', '-').replace('_', '-')
    text = re.sub(r'[-]+', '-', text)
    digits = re.sub(r'\D', '', text)
    if len(digits) == 9:
        return f"{digits[:3]}-{digits[3:5]}-{digits[5:]}"
    return None

def extract_ssn_from_image(filepath):
    image = cv2.imread(filepath)
    if image is None:
        print("Invalid image")
        return
    reader = easyocr.Reader(['en'], verbose=False)
    results = reader.readtext(image)

    for _, text, confidence in results:
        ssn = findSSN(text)
        if ssn:
            print(ssn)
            return
    print("NOT_FOUND")

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python extract_ssn.py <image_path>")
        sys.exit(1)

    image_path = sys.argv[1]
    if not Path(image_path).exists():
        print("Invalid path")
        sys.exit(1)

    extract_ssn_from_image(image_path)
