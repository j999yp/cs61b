public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        while (true) {
            Character a = deque.removeFirst();
            Character b = deque.removeLast();
            if (a == null || b == null) {
                break;
            }
            if (!a.equals(b)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        while (true) {
            Character a = deque.removeFirst();
            Character b = deque.removeLast();
            if (a == null || b == null) {
                break;
            }
            if (!cc.equalChars(a, b)) {
                return false;
            }
        }
        return true;
    }
}
