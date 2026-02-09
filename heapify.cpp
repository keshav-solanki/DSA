#include<iostream>

void heapify(int arr[],int n,int i)
{
    int largest = i;
    int lc = 2*i;
    int rc = 2*i+1;

    if(lc<=n && arr[lc]>arr[largest])
    {
        largest = lc;
    }
    
    if(rc<=n && arr[rc]>arr[largest])
    {
        largest = rc;
    }

    if(largest!=i)
    {
        swap(arr[largest],arr[i]);
        heapify(arr,n,largest);
    }
}
int main()
{
    int arr[9] = {-1,8,3,10,15,12,19,7,18};
    int n = 8;
    for(int i=n/2;i>0;i--)
    {
        heapify(arr,n,i);
    }

    for(int i =1;i<=n;i++)
    {
        cout<<arr[i]<<"   ";
    }
    cout<<endl;
}

