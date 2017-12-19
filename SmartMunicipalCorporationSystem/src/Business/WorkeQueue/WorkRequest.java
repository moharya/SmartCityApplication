/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkeQueue;


import Business.Address.Address;
import Business.UserAccount.UserAccount;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author raunak
 */
public abstract class WorkRequest {

    private String requestType;
    private String message;
    private UserAccount sender;
    private UserAccount receiver;
    private String status;
    private Date requestDate;
    private Date resolveDate;
    private String groupId;
    private String category;
    private Address address;
    private String providerName;

    public enum WorkRequestType{
        ElectricityWorkRequest("Electricity Work Request"), WaterSupplyWorkRequest("Water Supply Work Request"), ConstructionWorkRequest("Construction Work Request");
        private String value;
        private WorkRequestType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
    
    
    public WorkRequest(){
        requestDate = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.requestType);
        hash = 23 * hash + Objects.hashCode(this.getAddress().getAddressLine1());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorkRequest other = (WorkRequest) obj;
        if (!Objects.equals(this.requestType, other.requestType)) {
            return false;
        }
        if (!Objects.equals(this.getAddress().getAddressLine1(), other.getAddress().getAddressLine1())) {
            return false;
        } if(!this.getCategory().equals("Public") || !other.getCategory().equals("Public")){
            return false;
        }
        return true;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
   
    @Override
    public String toString() {
        return getAddress().getAddressLine1();
    }
    
    
}
