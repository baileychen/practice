class Solution:
    
    def isValid(self, s: str) -> bool:
        #
        # stack? popping/latest thing
        # Stack: ( |
        # If is left char: Add char to stack
        # If is right char: pop Stack
            # if left char and right char are a pair, continue
            # else: return false
        # return true
        
        # O(n) space & runtime
        leftChars = ["(", "{", "["];
        rightPair = {"(": ")", "{": "}", "[": "]"};
        stack = [];
        for i in range(len(s)):
            currChar = s[i];
            if currChar in leftChars:
                stack.append(currChar);
            else:
                if len(stack) == 0:
                    return False;
                leftChar = stack.pop();
                if currChar is not rightPair[leftChar]:
                    return False;
        return len(stack) == 0;