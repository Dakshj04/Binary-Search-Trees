
import java.util.ArrayList;




public class BST{
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }
    }
    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(root.data>val){
           root.left= insert(root.left, val);
        }
        else  {
          root.right= insert(root.right, val);
        }
        return root;
    }

  
    public static void inorder(Node root){
      if(root==null){
        return;
      }
      inorder(root.left);
      System.out.print(root.data+" ");
      inorder(root.right);
      

    }
    public static void preorder(Node root){
      if(root==null){
        return;
      }
      System.out.print(root.data+" ");
      inorder(root.left);
      inorder(root.right);
      

    }
    public static boolean searchBst(Node root, int key){
      if(root==null){
        return false;
      }
      if(root.data==key){
        return true;
      }
     if(root.data>key){
        return searchBst(root.left,key);
      }
    else {
        return searchBst(root.right,key);
      }
  
    }
    public static Node delete(Node root,int val){
      if(root==null) return null;
      if(root.data<val){
        root.right=delete(root.right, val);
      }
      else if(root.data>val){
        root.left=delete(root.left, val);
      }
      else{//voila
       //case 1 leaf node
       if(root.left==null&&root.right==null){
        return null;
       }

       //case 2 single child 
       if (root.left==null) {
         return root.right;
       }
       else if(root.right==null){
        return  root.left;
       }
       
       //case 3 both children
       Node IS = findInorderSuccessor(root.right);
       root.data=IS.data;
       root.right= delete(root.right, IS.data);
       
      }
      return root;
    } 
      public static Node findInorderSuccessor(Node root){
        while (root.left!=null) {
          root=root.left;
        }
        return root;
      }
      public static void printInRange(Node root,int k1, int k2){
        if (root==null){
          return;
        }

        if (root.data>=k1 && root.data<=k2) {
          printInRange(root.left, k1, k2);
          System.out.print(root.data+" ");
          printInRange(root.right, k1, k2);
        }
        else if (root.data<k1) {
            printInRange(root.right, k1, k2);

        }
        else{
          printInRange(root.left,k1,k2);
        }
      }
      public static boolean isValidBst(Node root,Node min,Node max){
         if(root==null){
          return true;
         }

         if (min!=null && root.data<=min.data) {
          return false;
         }
         else if (max!=null && root.data>=max.data) {
          return false;
         }
        
         return  isValidBst(root.left, min, root) && isValidBst(root.right , root, max);
      }

      public static Node mirrorBst(Node root){
        if (root==null) {
          return root;
        }
       Node leftsub=mirrorBst(root.left);
        Node rightsub=mirrorBst(root.right);
        root.left=rightsub;
        root.right=leftsub;

        return root;
      }
       public static Node sortedArrayToBST(int[] nums,int start,int end) {
        if(start>end){
            return null;
        }
        int mid=(start+end)/2;
        Node newNode=new Node(nums[mid]);
       newNode.left= sortedArrayToBST(nums,start,mid-1);
       newNode.right= sortedArrayToBST(nums,mid+1,end);
        return newNode;
         
    }   
   public static void getInorder(Node root,ArrayList<Integer> inorder){
    if(root==null){
      return;
    }
    getInorder(root.left, inorder);
    inorder.add(root.data);
    getInorder(root.right, inorder);
   }

   public static Node createBST(ArrayList<Integer> inorder,int st,int end){
    if(st>end){
      return null;
    }
     int mid=(st+end)/2;
     Node root= new Node(inorder.get(mid));
      createBST(inorder, st, mid-1);
     createBST(inorder, mid+1, end);

     return root;
      
   }
    public static Node balanceBST(Node root){
      //inorder sequence
       ArrayList<Integer> inorder=new ArrayList<>();
        getInorder(root, inorder);
         

      //sorted inorder -> balanced bst
      return root;
    }
  public static void main(String[] args) {
   
//  int values[]={8,5,3,1,4,6,10,11,14};
//  Node root=null;

//  for (int i = 0; i < values.length; i++) {
//     root=insert(root, values[i]);
//  }
// Manually constructing the BST for demonstration
Node root = new Node(8);
root.left = new Node(5);
root.left.left = new Node(3);
root.left.left.left = new Node(1);
root.left.left.right = new Node(4);
root.left.right = new Node(6);
root.right = new Node(10);
root.right.right = new Node(11);
root.right.right.right = new Node(12);

 inorder(root);
 System.out.println();
System.out.println(searchBst(root, 8));

  //  root=delete(root, 8);
  
   inorder(root);
   System.out.println();
   System.out.println("Print in range");
   printInRange(root, 5,12);
   System.out.println();
   if (isValidBst(root, null, null)){
    System.out.println("Valid");
   }
   else{
    System.out.println("Not Valid");
   }
   mirrorBst(root);
   preorder(root);
   System.out.println();
   int nums[]= {-10,-3,0,5,9};
   Node root2=sortedArrayToBST(nums,0,nums.length-1);
   inorder(root2);
  
}}