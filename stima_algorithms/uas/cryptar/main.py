def solve_cryptarithmetic():
    # Order variables column by column (Right-to-Left)
    # Ones: O, R | Tens: W, U | Hundreds/Thousands: T, F
    variables = ['O', 'R', 'W', 'U', 'T', 'F']
    assignment = {}
    used_digits = set()

    def is_feasible(var, val):
        """ The Bounding Function """
        # 1. Uniqueness bound
        if val in used_digits:
            return False
            
        # 2. Leading zero bound
        if var in ['T', 'F'] and val == 0:
            return False

        # Temporarily place value to check column arithmetic
        assignment[var] = val

        # 3. Column arithmetic bounds
        # Check Ones Column as soon as O and R are assigned
        if 'O' in assignment and 'R' in assignment:
            if (assignment['O'] + assignment['O']) % 10 != assignment['R']:
                del assignment[var]
                return False
                
        # Check Tens Column as soon as W and U are assigned
        if 'W' in assignment and 'U' in assignment and 'O' in assignment:
            c1 = (assignment['O'] + assignment['O']) // 10
            if (assignment['W'] + assignment['W'] + c1) % 10 != assignment['U']:
                del assignment[var]
                return False

        # Check Final Columns when everything is filled
        if len(assignment) == 6:
            c1 = (assignment['O'] + assignment['O']) // 10
            c2 = (assignment['W'] + assignment['W'] + c1) // 10
            
            hundreds_match = (assignment['T'] + assignment['T'] + c2) % 10 == assignment['O']
            thousands_match = (assignment['T'] + assignment['T'] + c2) // 10 == assignment['F']
            
            if not (hundreds_match and thousands_match):
                del assignment[var]
                return False

        # Clean up temporary assignment if it passed the look-ahead checks
        del assignment[var]
        return True

    def backtrack(index):
        # Base Case: All variables successfully assigned
        if index == len(variables):
            return True

        var = variables[index]
        for val in range(10):
            if is_feasible(var, val):
                # Make assignment
                assignment[var] = val
                used_digits.add(val)

                # Move to next variable
                if backtrack(index + 1):
                    return True

                # Undo assignment (Backtrack)
                del assignment[var]
                used_digits.remove(val)
                
        return False

    if backtrack(0):
        print("Solution Found:", assignment)
        print(f"  {assignment['T']}{assignment['W']}{assignment['O']}")
        print(f"  {assignment['T']}{assignment['W']}{assignment['O']}+")
        print("  -----")
        print(f" {assignment['F']}{assignment['O']}{assignment['U']}{assignment['R']}")
    else:
        print("No solution exists.")

solve_cryptarithmetic()
