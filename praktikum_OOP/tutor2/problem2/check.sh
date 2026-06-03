
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo "Compiling..."
g++ *.cpp -o app

if [ $? -ne 0 ]; then
    echo -e "${RED}Compilation failed!${NC}"
    exit 1
fi

echo "Comparing output..."
./app > actual.txt

if diff -u actual.txt expected.txt; then
    echo -e "${GREEN}✅ 100% MATCH! You're good to submit.${NC}"
else
    echo -e "${RED}❌ DIFFERENCE FOUND! See the +/- above.${NC}"
fi

# Cleanup
rm actual.txt
