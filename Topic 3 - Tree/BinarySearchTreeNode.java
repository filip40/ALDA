//Filip Lingefelt, fili8261

/**
 *
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) Det är också den enda av klasserna ni
 * ska lämna in. Glöm inte att namn och användarnamn ska stå i en kommentar
 * högst upp, och att en eventuell paketdeklarationen måste plockas bort vid inlämningen för
 * att koden ska gå igenom de automatiska testerna.
 *
 * De ändringar som är tillåtna är begränsade av följande:
 * <ul>
 * <li>Ni får INTE byta namn på klassen.
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans. Detta gäller också alterntiv
 * till loopar, så som strömmar.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * <li>Ni får INTE låta NÅGON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni väljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer är
 * tillåtna.
 * </ul>
 *
 * @author henrikbe
 *
 * @param <T>
 */
//@SuppressWarnings("unused") // Denna rad ska plockas bort. Den finns här
// tillfälligt för att vi inte ska tro att det är
// fel i koden. Varningar ska normalt inte döljas på
// detta sätt, de är (oftast) fel som ska fixas.
public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;                         //instansvariabler
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        int compared = data.compareTo(this.data);

        if(compared < 0){
            if(left != null){
                return left.add(data);
            }else{
                left = new BinarySearchTreeNode<>(data);
                return true;
            }
        }else if(compared > 0){
            if(right != null){
                return right.add(data);
            }else{
                right = new BinarySearchTreeNode<>(data);
                return true;
            }
        }else{
            return false;
        }
    }

    private T findMin() {
        if(left == null){
            return data;
        }else{
            return left.findMin();
        }
    }

    public BinarySearchTreeNode<T> remove(T data) {
        if (data == null){
            return null;
        }
        int compared = data.compareTo(this.data);

        if(compared < 0) {
            if (left != null) {
                left = left.remove(data);
                return this;
            }
        } else if(compared > 0) {
            if (right != null) {
                right = right.remove(data);
                return this;
            }
        }else {
            if (left == null && right == null) { //leaf
                return null;
            } else if (left != null && right != null) { //two
                T temp = right.findMin();
                right = right.remove(temp);
                this.data = temp;
            } else { //one, left n right
                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return left;
                }
            }
        }
        return this;
    }

    public boolean contains(T data) {
        if(data == null){
            return false;
        }
        int compared = data.compareTo(this.data);
        if(compared == 0) {
            return true;
        }else if(compared < 0 && left != null){
            return left.contains(data);
        }else if(compared > 0 && right != null){
            return right.contains(data);
        }else{
            return false;
        }
    }

    public int size() {
        int size = 0;
        if(data != null){
            size++;
            if(left != null){
                size += left.size();
            }
            if(right != null){
                size += right.size();
            }
        }
        return size;
    }

    public int depth() {
        int depthLeft = 0;
        int depthRight = 0;
        int depth = 0;
        if(left != null){
            depthLeft = left.depth();
            depthLeft++;
        }
        if(right != null){
            depthRight = right.depth();
            depthRight++;
        }
        if(depthRight>depthLeft){
            depth = depthRight;
        }else{
            depth = depthLeft;
        }
        return depth;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(data != null){
            if (left != null) {
                sb.append(left);
                sb.append(", ");
            }
            sb.append(data);
            if(right != null){
                sb.append(", ");
                sb.append(right);
            }
        }
        return "" + sb ;
    }

}
