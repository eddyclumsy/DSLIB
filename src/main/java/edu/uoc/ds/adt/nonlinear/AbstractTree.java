package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.*;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.IteratorTraversalImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public abstract class AbstractTree<E> implements Tree<E> {


    /**
     * Check if the tree or subtree has a child.
     *
     * @param node reference position
     * @return true if the position has any child and false otherwise
     */
    public boolean isLeaf(Position<E> node) {
        return !children(node).hasNext();
    }


    public boolean isEmpty() {
        return root() == null;
    }


    /**
     * Method that returns the number of children in a node.
     *
     * @param node reference position
     * @return number of children, or zero if s a leaf
     * @throws InvalidPositionException whether the position is null or not
     *                                  valid
     */
    public int numChildren(Position<E> node) {
        int n = 0;
        Traversal<E> siblings = children(node);
        while (siblings.hasNext()) {
            n++;
            siblings.next();
        }
        return n;
    }


    /**
     *
     * Retrieves the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code: <PRE>
     *  for (Iterator it = adt.values(); it.hasNext();)
     *      System.out.println (it.next ()); </PRE>
     *  To enumerate is simply to enunciate one after the other (things
     *  of a series, the parts of a whole). But if the container is defined
     *  some sort of order or route, the enumeration must be
     *  consequent and offer the items in order (FIFO, LIFO, order,
     *  etc.), without altering the current state of the container.
     *
     * @return enumeration of the elements of the preorder container
     * @throws InvalidPositionException if you want to get the next
     *                      element of the enumeration and there is none
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<E> values() {
        return new IteratorTraversalImpl<>(preOrderTraversal());
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of container positions by level
     */
    public Traversal<E> positions() {
        return new LevelsTraversal<>(this);
    }


    public Traversal<E> preOrderTraversal() {
        return new PreOrderTraversal<>(this);
    }


    public Traversal<E> postOrderTraversal() {
        return new PostOrderTraversal<>(this);
    }


    public Traversal<E> levelOrderTraversal() {
        return new LevelsTraversal<>(this);
    }


    public int size(Position<E> posicio) {
        int n = 1;
        Traversal<E> r = children(posicio);
        while (r.hasNext())
            n += size(r.next());
        return n;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (root() != null)
            toString(sb, root());
        sb.append("}");
        return sb.toString();
    }


    protected void toString(StringBuilder sb, Position<E> posicio) {
        sb.append(posicio.toString() + " ");
        if (!isLeaf(posicio)) {
            sb.append("{");
            Traversal<E> r = children(posicio);
            while (r.hasNext()) {
                toString(sb, r.next());
                if (r.hasNext())
                    sb.append(",");
            }
            sb.append("}");
        }
    }


    /**
     *
     * Class that provides the basic behavior for all three routes
     * preorder, inorder and postorder. In this class the
     * behavior as in all three routes, so that later
     * It will only be necessary to redefine, for each specific route, the method
     * next.
     *
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected abstract static class BasicTraversal<E> implements Traversal<E> {
        /**
         * The tree to traverse.
         */
        protected Tree<E> tree;


        /**
         * Auxiliary stack. This stack stores the items we tried but
         * we haven't traveled yet. The top of the stack should always have
         * the next element to go through. All three pre / in / post-order routes
         * can be seen from the following way: given a node, it has a set of
         * descendants that will appear before he on the route (they are more
         * priority) and a set of descendants that will appear
         * after him on the route (they are less priority).
         */
        protected Stack<Position<E>> stack;

        /**
         * Constructor.
         *
         * @param tree The tree to traverse.
         */
        protected BasicTraversal(Tree<E> tree) {
            this.tree = tree;
            stack = new StackArrayImpl<>(tree.size());
            if (!tree.isEmpty()) {
                stack.push(tree.root());
                pushSiblingsGreaterPriority(tree.root());
            }
        }

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
        protected abstract void pushSiblingsGreaterPriority(Position<E> pare);


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
        protected abstract void pushSiblingsLessPriority(Position<E> pare);


        /**
        * This method stacks the children of a node in the
         * order in which they are defined.
         * @param parent
         */

        protected void pushSiblings(Position<E> parent) {
            Traversal<E> siblings = tree.children(parent);

            LinkedList<Position<E>> stackSiblings = new LinkedList<>();
            while (siblings.hasNext()) {
                Position<E> child = siblings.next();
                stackSiblings.insertBeginning(child);
            }

            Iterator<Position<E>> childrens2 = stackSiblings.values();
            while (childrens2.hasNext()) {
                Position<E> child = childrens2.next();
                stack.push(child);
            }
        }


        /**
         * Check for a first or next position. It is sensitive to
         * possible alterations to the position structure. Return false
         * if the container is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can move forward or not
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }


        /**
         * Forward first, if possible, and then return to position.
         * If there is no next position throw an exception.
         *
         * @return next position
         * @pre hiHaSeguent(), ExcepcioPosicioInvalida
         */
        public Position<E> next() {
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
    protected static class PreOrderTraversal<E> extends BasicTraversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. The detector is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Constructor.
         *
         * @param tree The tree to traverse.
         */
        public PreOrderTraversal(Tree<E> tree) {
            super(tree);
        }


        /**
         * In the case of the preorder path, given a node, this always
         * will appear before his descendants on the route,
         * therefore, a node has no descendants with higher priority than it.
         */
        protected void pushSiblingsGreaterPriority(Position<E> parent) {
            throw new UnsupportedOperationException();
        }


        /**
         * In the case of the preorder route, the descendants with less
         * priority that a node is its two children (and at the same time the
         * descendants of these). We just have to stack the two children,
         * then later when we reach these children on the way
         * we will treat them and treat their children properly.
         */
        protected void pushSiblingsLessPriority(Position<E> pare) {
            pushSiblings(pare);
        }
    }

    /**
     * Class that provides a traversal of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the structure of positions.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class PostOrderTraversal<E> extends BasicTraversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. The detector is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         *  auxiliary stack used for the route both the children with the
         *  highest priority and the children with the least
         *  priority
         */
        private Stack<Position<E>> nodesAlreadyDeployed;


        /**
         * Constructor.
         *
         * @param tree The tree to traverse
         */
        public PostOrderTraversal(Tree<E> tree) {
            super(tree);
        }


        /**
         * In the case of the post-order path, given a node, this always
         * will appear after his descendants on the route,
         * therefore, all children in a node have higher priority than he.
         */
        protected void pushSiblingsGreaterPriority(Position<E> parent) {
            pushSiblings(parent);
            while (!isDeployed(stack.peek()))
                pushSiblings(stack.peek());
        }


        /**
         * In the case of the postorder path a node has no descendants with
         * less priority.
         */
        protected void pushSiblingsLessPriority(Position<E> pare) {
            throw new UnsupportedOperationException();
        }


        @Override
        protected void pushSiblings(Position<E> parent) {
            if (nodesAlreadyDeployed == null)
                nodesAlreadyDeployed = new StackArrayImpl<>(tree.size());
            super.pushSiblings(parent);
            nodesAlreadyDeployed.push(parent);
        }


        @Override
        public Position<E> next() {
            Position<E> node = stack.pop();
            nodesAlreadyDeployed.pop();
            if (!stack.isEmpty() && !isDeployed(stack.peek())) {
                pushSiblingsGreaterPriority(stack.peek());
            }
            return node;
        }


        protected boolean isDeployed(Position<E> node) {
            return !nodesAlreadyDeployed.isEmpty() && nodesAlreadyDeployed.peek() == node;
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
         * serializable of the same class. The detector is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * @param tree The tree to traverse
         */
        Tree<E> tree;


        /**
         * Auxiliary Queue
         */
        protected Queue<Position<E>> queue;


        /**
         * Constructor without parameters.
         */
        public LevelsTraversal(Tree<E> tree) {
            this.tree = tree;
            queue = new QueueArrayImpl<>(tree.size());
            if (!tree.isEmpty()) queue.add(tree.root());
        }

        /**
         * Check for a first or next position. It is sensitive to
         * possible alterations to the position structure. Return false
         * if it is empty or the last position has already been visited.
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
         * @return next position
         */
        public Position<E> next() throws InvalidPositionException {
            if (!hasNext()) throw new InvalidPositionException("There is no data");

            Position<E> parent = queue.poll();
            for (Traversal<E> r = tree.children(parent); r.hasNext(); ) {
                queue.add(r.next());
            }
            return parent;
        }
    }

}
