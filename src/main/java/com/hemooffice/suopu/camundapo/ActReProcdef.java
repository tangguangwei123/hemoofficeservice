package com.hemooffice.suopu.camundapo;

public class ActReProcdef {
    private String id;

    private Integer rev;

    private String category;

    private String name;

    private String key;

    private Integer version;

    private String deploymentId;

    private String resourceName;

    private String dgrmResourceName;

    private Byte hasStartFormKey;

    private Integer suspensionState;

    private String tenantId;

    private String versionTag;

    private Integer historyTtl;

    private Boolean startable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId == null ? null : deploymentId.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getDgrmResourceName() {
        return dgrmResourceName;
    }

    public void setDgrmResourceName(String dgrmResourceName) {
        this.dgrmResourceName = dgrmResourceName == null ? null : dgrmResourceName.trim();
    }

    public Byte getHasStartFormKey() {
        return hasStartFormKey;
    }

    public void setHasStartFormKey(Byte hasStartFormKey) {
        this.hasStartFormKey = hasStartFormKey;
    }

    public Integer getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getVersionTag() {
        return versionTag;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag == null ? null : versionTag.trim();
    }

    public Integer getHistoryTtl() {
        return historyTtl;
    }

    public void setHistoryTtl(Integer historyTtl) {
        this.historyTtl = historyTtl;
    }

    public Boolean getStartable() {
        return startable;
    }

    public void setStartable(Boolean startable) {
        this.startable = startable;
    }
}