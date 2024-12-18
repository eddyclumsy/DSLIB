package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.IteratorTraversalImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

/**
 * Abstract class that defines the operations of any binary tree,
 * which is characterized by organizing its elements (nodes)
 * forming a hierarchy: every node (except the root that is the head of the
 * hierarchy) is descending from a single node, and can be ascending from a maximum
 * of two nodes (when it has no descendants it is called a leaf).
 * <p>
 * Implements the usual routes and other operations that
 * are derived from the basic operations already defined at this level of
 * the hierarchy.
 * <p>
 * It also implements the Serializable interface to be able to convert to
 * strings or streams of bytes (streams) the objects in the container and
 * record or transmit them.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public abstract class BinaryTree<E> extends AbstractTree<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. The detector is calculated
     * using a method of the Utilities class.

     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Add an item as the left child of the received position, if any
     * can.
     *
     * @param parent reference position
     * @param elem item to add to the tree
     * @return new child left
     */
    public abstract Position<E> addLeftChild(Position<E> parent, E elem);

    /**
     * Add an item as the right child of the received position, if possible.
     *
     * @param parent to be the reference position
     * @param elem item to add to the tree
     * @return new child right
     */
    public abstract Position<E> addRightChild(Position<E> parent, E elem);

    /**
     * Retrieves the left child from a tree position.
     *
     * @param pos reference position
     * @return left child of received position; or null if you don't have one
     */
    public abstract Position<E> leftChild(Position<E> pos);

    /**
     * Retrieves the right child from a tree position.
     *
     * @param pos reference position
     * @return right son of position received; or null if you don't have one
     */
    public abstract Position<E> rightChild(Position<E> pos);


     /**
      * Method that supports multiple paths, from chindren positions
      * of the reference position, simultaneous and independent of each other.
      *
      * @param parent to be the reference position
      * @return enumeration of child positions
      * @throws InvalidPositionException whether the position is null or not
      * valid
     */
    public Traversal<E> children(Position<E> parent) {
        return new SiblingsTraversal<>(this, parent);
    }

    /**
     * Check if the tree or subtree has a child.
     *
     * @param node position node
     * @return false or true, depending on whether or not you have a child
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    @Override
    public boolean isLeaf(Position<E> node) {
        return (leftChild(node) == null) && (rightChild(node) == null);
    }

    /**
     * Retrieves the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code: <PRE>
     *   for (Iterator it = adt.values(); it.hasNext();)
     *     System.out.println (it.next ()); </PRE>
     *
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return the items in the container (inOrder)
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none or none
     * m�s
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    @Override
    public Iterator<E> values() {
        return new IteratorTraversalImpl<>(inOrderTraversal());
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of container positions by level
     * @see LevelsTraversal
     */
    @Override
    public Traversal<E> positions() {
        return new LevelsTraversal<>(this);
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of preorder container positions
     * @see PreOrderTraversal
     */
    @Override
    public Traversal<E> preOrderTraversal() {
        return new PreOrderTraversal<>(this);
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of messy container positions
     * @see InOrderTraversal
     */
    public Traversal<E> inOrderTraversal() {
        return new InOrderTraversal<>(this);
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of post-order container positions
     * @see PostOrderTraversal
     */
    @Override
    public Traversal<E> postOrderTraversal() {
        return new PostOrderTraversal<>(this);
    }

    /**
     * Class that provides a traversal of the children positions. Based
     * in the Iterator pattern, supports multiple simultaneous paths and
     * independent of the container. It is sensitive to possible alterations of
     * the structure of positions.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class SiblingsTraversal<E> implements Traversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * The tree to traverse
         */
        BinaryTree<E> tree;


        /**
         * Queue for storing children.
         */
        private Queue<Position<E>> children;

        /**
         * Constructor with a parameter.
         *
         * @param parent non-null reference position
         */
        public SiblingsTraversal(BinaryTree<E> tree, Position<E> parent) {
            this.tree = tree;
            children = new QueueArrayImpl<>(2);
            if (tree.leftChild(parent) != null)
                children.add(tree.leftChild(parent));
            if (tree.rightChild(parent) != null)
                children.add(tree.rightChild(parent));
        }

        /**
         * Check for a first or next position. It is sensitive to
         * possible alterations to the position structure. Return false
         * if it is a leaf or the last position has already been visited.
         *
         * @return true or false, depending on whether you can advance or not
         */
        public boolean hasNext() {
            return !children.isEmpty();
        }

        /**
         * Forward first, if possible, and then return to position.
         * If there is no next position throw an exception.
         *
         * @return next position
         * @pre hasNext(), InvalidPositionException
         */
        public Position<E> next() throws InvalidPositionException {
            return children.poll();
        }
    }

    /**
     * Class that provides a tour of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the structure of positions.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class LevelsTraversal<E> implements Traversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * The tree to traverse
         */
        BinaryTree<E> tree;


        /**
         * Auxiliary queue.
         */
        protected Queue<Position<E>> queue;

        /**
         * Constructor without parameters.
         */
        public LevelsTraversal(BinaryTree<E> tree) {
            this.tree = tree;
            queue = new QueueArrayImpl<>(tree.size());
            if (!tree.isEmpty()) queue.add(tree.root());
        }

        /**
         * Check for a first or next position. It is sensitive to
         * possible alterations to the position structure. Return false
         * if the container is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can advance or not
         */
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        /**
         * Forward first, if possible, and then return to position.
         * If there is no next position throw an exception.
         *
         * @return following position
         * @pre hasNext(), InvalidPositionException
         */
        public Position<E> next() throws InvalidPositionException {
            Position<E> parent = queue.poll();
            if (tree.leftChild(parent) != null)
                queue.add(tree.leftChild(parent));
            if (tree.rightChild(parent) != null)
                queue.add(tree.rightChild(parent));
            return parent;
        }
    }


    /**
     * Class that provides the basic behavior for all three routes
     * preorder, inorder and postorder. In this class the
     * behavior as in all three routes, so that later
     * It will only be necessary to redefine, for each specific route, the method
     * next.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected abstract static class BasicOrderTraversal<E> implements Traversal<E> {
        /**
         *The tree to traverse
         */
        protected BinaryTree<E> tree;


        /**
         * Auxiliary Stack. This stack stores the items we tried but
         * we haven't visited yet. The top of the stack should always have
         * the following element to traversal. All three pre / in / post-order
         * routes can be seen from the following way: given a node, it has a set
         * of descendants that will appear before he on the route (they are more
         * priority) and a set of descendants that will appear after him
         * on the route (they are less priority).
         */
        protected Stack<Position<E>> stack;

        /**
         * This method stacks the descendants of a node that must appear
         * before him in the path of the tree. This feature
         * differentiates the three routes preorder, inorder and
         * postorder.
         * Therefore, the method is defined in this class as abstract
         * and must be redefined in the derived classes as appropriate.
         * The other methods that implement the path can be defined in
         * this same class based on this abstract method.
         */
        protected abstract void pushSiblingsWithLessPriority(Position<E> parent);


        /**
         * This method stacks the descendants of a node that must appear
         * after him in the path of the tree. This feature
         * This is the other feature that differentiates the three preorder routes,
         * inorder and postorder.
         * Therefore, the method is defined in this class as abstract
         * and must be redefined in the derived classes as appropriate.
         * The other methods that implement the path can be defined in
         * this same class based on this abstract method.
         */
        protected abstract void pushSiblingsLessPriority(Position<E> parent);


        /**
         * Constructor.
         *
         * @param tree The tree to traverse
         */
        protected BasicOrderTraversal(BinaryTree<E> tree) {
            this.tree = tree;
            stack = new StackArrayImpl<>(tree.size());
            if (!tree.isEmpty()) {
                stack.push(tree.root());
                pushSiblingsWithLessPriority(tree.root());
            }
        }


        /**
         * Check for a first or next position. S sensitive to
         * possible alterations to the position structure. Return false
         * if the container is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can advance or not
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }


        /**
         * Forward first, if possible, and then return to position.
         * If there is no next position throw an exception.
         *
         * @return following position
         * @pre hasNext(), InvalidPositionException
         */
        public Position<E> next() {
            if (stack.isEmpty())
                throw new InvalidPositionException();
            Position<E> node = stack.pop();
            pushSiblingsLessPriority(node);
            return node;
        }

    }


    /**
     * Class that provides a tour of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the structure of positions.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class PreOrderTraversal<E> extends BasicOrderTraversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Constructor.
         *
         * @param tree The tree to traverse
         */
        public PreOrderTraversal(BinaryTree<E> tree) {
            super(tree);
        }


        /**
         * In the case of the preorder path, given a node, this always
         * will appear before his descendants on the route,
         * therefore, a node has no descendants with higher priority than it.
         */
        protected void pushSiblingsWithLessPriority(Position<E> parent) {

        }


        /**
         * In the case of the preorder route, the descendants with less
         * priority that a node is its two children (and at the same time the
         * descendants of these). We just have to stack the two kids,
         * then later when we reach these children on the way
         * we will treat them and treat their children properly.
         */
        protected void pushSiblingsLessPriority(Position<E> pare) {
            Position<E> child = tree.rightChild(pare);
            if (child != null) stack.push(child);
            child = tree.leftChild(pare);
            if (child != null) stack.push(child);
        }
    }

    /**
     * Class that provides a tour of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the position structure.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class InOrderTraversal<E> extends BasicOrderTraversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Constructor.
         *
         * @param tree The tree to traverse
         */
        public InOrderTraversal(BinaryTree<E> tree) {
            super(tree);
        }


        /**
         * Descendants with the highest priority in the case of the route
         * disorder is the left child and his descendants. So,
         * we stack the left child. Later, if the child left
         * you have a descendant with the highest priority, we will have to stack it as well.
         * This results in, in this case, stacking the left descendants;
         * that is, the left child, the left child of the left child, etc.
         */
        protected void pushSiblingsWithLessPriority(Position<E> parent) {
            Position<E> child = tree.leftChild(parent);
            while (child != null) {
                stack.push(child);
                parent = child;
                child = tree.leftChild(parent);
            }
        }

        /**
         * Descendants with less priority for the case of the route
         * disorder is the right child and his descendants. So,
         * we stack the right child. Immediately afterwards we proceed to stack the descendants
         * of the right child who have more priority than this.
         */
        protected void pushSiblingsLessPriority(Position<E> parent) {
            Position<E> child = tree.rightChild(parent);
            if (child != null) {
                stack.push(child);
                pushSiblingsWithLessPriority(child);
            }
        }

    }


    /**
     * Class that provides a tour of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the structure of positions.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class PostOrderTraversal<E> extends InOrderTraversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Last node from which they have been stacked on the stack used internally
         * for the route both the children with the highest priority and the children with the least
         * priority
         */
        private Stack<Position<E>> nodesAlreadyDeployed;


        /**
         * Constructor.
         *
         * @param tree The tree to traverse
         */
        public PostOrderTraversal(BinaryTree<E> tree) {
            super(tree);
        }


        /**
         * Redefine this method to take descendants into account
         * with less priority than the last node in the left branch.
         */
        @Override
        protected void pushSiblingsWithLessPriority(Position<E> parent) {
            super.pushSiblingsWithLessPriority(parent);
            pushSiblingsLessPriority(stack.peek());
        }


        /**
         * Descendants with less priority for the case of the route
         * disorder is the right child and his descendants. So,
         * we stack the right child. Immediately afterwards we proceed to stack the descendants
         * of the right child who have more priority than this.
         */
        @Override
        protected void pushSiblingsLessPriority(Position<E> parent) {
            if (nodesAlreadyDeployed == null)
                nodesAlreadyDeployed = new StackArrayImpl<>(tree.size());
            nodesAlreadyDeployed.push(parent);
            super.pushSiblingsLessPriority(parent);
        }


        /**
         * This class reuses the priorities set by the route
         * mess up and modify the following method. Modification consists of
         * stack the items with the lowest priority of the remaining node at the top
         * of the stack after unstacking the current one.
         * That is, what we are doing is changing the meaning of the methods
         * stack DescendantsWithMore / LessPriority to make the most of
         * code possible. In this class all the descendants of a node
         * have more priority than this. Then we divide their descendants
         * in two groups: those with the highest priority (left child and descendants)
         * and those who have less (right child and descendants).
         * If we wanted to respect the structure defined in the base class we would finish
         * having a stack that would initially have all the nodes in the tree.
         *
         * @return following position
         * @pre hasNext(), InvalidPositionException
         */
        @Override
        public Position<E> next() {
            Position<E> node = stack.pop();
            nodesAlreadyDeployed.pop();
            if (!stack.isEmpty() && !isTotallyDeployed(stack.peek())) {
                pushSiblingsLessPriority(stack.peek());
            }
            return node;
        }


        protected boolean isTotallyDeployed(Position<E> node) {
            return !nodesAlreadyDeployed.isEmpty() && nodesAlreadyDeployed.peek() == node;
        }


    }


    @Override
    protected void toString(StringBuilder sb, Position<E> posicio) {
        if (posicio != null) {
            sb.append(posicio.toString() + " ");
            sb.append("{");
            toString(sb, leftChild(posicio));
            sb.append(",");
            toString(sb, rightChild(posicio));
            sb.append("}");
        }
    }

}
