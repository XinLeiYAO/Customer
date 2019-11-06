package com.example.asus.customer.entity;

public class KeHuMessageNewBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : {"ortherInfomationPojo":{"ci_ElectronicCommerce":"4","ci_clientType":"0","ci_Stage":"6","ci_TurnStatus":"0","ci_proRequirement":"","ci_LFMoney":"0.00","mobile":"18510172455","ca_AuditTime":"2018-11-01 17:38:08.133","ca_SjsSubTime":"2018-11-16 14:42:57.663","ci_ReciveStatus":"1","ci_ReceiveTime":"2018-10-31 15:43:57.667","receiveTime":"1","ca_AuditState":"4","ca_FangYuanAddress":"省/市-区/县-商圈-诺德-1-1-","ci_State":"10","ci_AddTime":"2018-10-31 14:45:47.0"},"baseInformation":{"ci_ClientName":"思创乐高","ci_DesignerCard":"01011085","ci_SalesmanName":"电商","ca_SWIndustryTypeID":"商业店铺","ci_ConstructionState":"3","ci_APP":"1","ci_Type":"商业","ca_HangYeLeIXing":"","tb_diqu":"R6","tb_diquId":11,"ca_LeaseTermEnd":"-","ca_LeaseTermStart":"-","ci_OfficeAddress":"","ci_TypeLevelThird":"","ci_SalesmanCard":"02200100","ci_TypeID":"3","ci_DesignerName":"王秀","ci_DesignState":"0","ci_DesignerSex":"女"},"propertyInformation":{"ca_DesignatedFireCompany":"是","ca_FireProtectionGrade":"","ca_TwoManagerTel":"53033899","ca_Aptitude":"二级","ca_Fence":"","ca_property_management_company":"","ca_ProtectiveMaterial":"12mm石膏板","ca_Elevator":"免费","ca_DrawAudit":"有","ca_DesignatedAirCompany":"是","ca_ProductProtection":"是","ca_HtRiskPrice":"0","ca_FireUnitName":"","ca_PropertyCost":"0","ca_Blueprint":"蓝图","ca_PropertyInsurance":"是","ca_InsuranceCompanyName":"","ca_LeadName":"李经理","ca_GarbageTransport":"","ca_HtBlowdownPrice":"0","ca_InsuranceCompany":"否","ca_EnvirProtectionGrade":"","ca_SpecialRequirement":"无","ca_ReqConTime":"晚上","ca_Maintenance":"无","ca_AirUnitName":""},"companyInformation":{"ca_EnterpriseNature":"私企","ca_EstablishedYear":"","ca_ForeignEnterprises":"否"},"buildingInformation":{"ca_RealEstate":"诺德","ca_DevelopmentFloor":"1","ca_RealEstateId":"","ca_HouseNumber":"","ca_RealEstatePeriod":"1","ca_FloorHeight":""},"customerInformation":{"ci_proAge":"30岁-40岁","ca_Authority":"","ca_proHeadIdentity":"老板","ci_proHeadTel":"","ca_NoteFocus":"价格","ca_proHeadCharacter":"","ci_proSex":"女","ci_proHead":"刘女士","ca_Sincerity":"","ca_ContractPerson":"否","ca_TakingRole":"","ci_proCharacteristic":"文艺范"},"measureInformation":{"ca_windowsillHight":"200","ca_TuyereMinimumHeight":"3000","ca_MinimumSprayHeight":"3100","ca_WindowType":"落地窗","ca_SpaceMinHeight":"0.00","ca_OccupyPublicCorridor":"否","ca_UpWaterSpot":"1","ca_DownWaterSpot":"1","ca_OriginalTopMaterial":"水泥毛坯","ca_WaterPath":"0","ca_CargoDoorHight":"0","ca_isGroundSmooth":"否","ca_WindowHight":"4000","ca_DownWaterSpotSize":"15.00","ca_WindowWidth":"1200.00","ca_StrongBoxNum":"1","ca_OriginalGroundMaterial":"水泥毛坯","ca_BuildingStructure":"","dataPercentNew":"82.72","ca_SecondaryHeight":"3200","ca_mainBeamHeight":"3500","ca_SpaceMaxHeight":"4000","ca_OriginalGround":"是","ca_OriginalWallMaterial":"","ca_HouseOrientation":"东","ca_WeakBoxNum":"0","ca_CargoDoorWide":"0","ca_CurtainWallSpacing":"1200.00","ca_GroundElevation":"15"},"decorateInformation":{"ca_SpecificOpeningTime":"1900-01-01 00:00:00.000","ca_InviteTenders":"否","ca_swRemarks":"小孩玩具店,物业要求施工二级资质,使用面积150平,毛坯,已租,问预算没有说,需求很简单,就是基础的装修,没有太多的设计成份.我给报价600至1000不等.加微信客户给发位置","ca_IndustryPositioning":"单店","ca_PlannedOpeningTime":"","ca_Competitors":"0","ca_DesignatedBrand":"","ca_SpecificDecoration":"具体需求","ca_DecorationDate":"-","ca_proAttribute":"正常单","ci_DecorationAddress":"省/市-区/县-商圈-诺德-1-1-","ca_PlanInvest":"","ca_IntelligentWeakCurrent":"无需求","ca_DesignFeeScope":"","ca_SpaceRequirement":"卫生间，储物间","ca_FireDemand":"","ca_DecBudgetPrice":"10.00","ca_IntentionalStyle":"现代简约","ca_DesignFee":"0","ca_HtWorkCycle":"","ca_Aim":"","ca_FengShuiRequirements":"否","ci_advancePayment":"否","ca_AirConditioningDemand":"","ca_PlannedStartTime":"","ca_SoftFurniture":"无需求","ca_SpecificStartTime":"2019-06-30 13:57:17.810"},"housingResourcesInformation":{"ca_Money":"0.00","ca_AvailabilityStatus":"已定","ca_RentFreeDate":"","ca_HousingAddress":"","ci_Area":"150.00","ca_MeasureDate":"1900-01-01","ca_LeaseTerm":"","ca_HousingType":"局部改造","ca_TransactionType":"租","ca_HousingProvince":"","ca_Rents":"","ca_HousingCity":"","ca_LaunchTime":"1900-01-01","ca_HousingTradingArea":"","ci_BuildingArea":"160.00"}}
     */

    private String StatusMsg;
    private int StatusCode;
    private BodyBean Body;

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * ortherInfomationPojo : {"ci_ElectronicCommerce":"4","ci_clientType":"0","ci_Stage":"6","ci_TurnStatus":"0","ci_proRequirement":"","ci_LFMoney":"0.00","mobile":"18510172455","ca_AuditTime":"2018-11-01 17:38:08.133","ca_SjsSubTime":"2018-11-16 14:42:57.663","ci_ReciveStatus":"1","ci_ReceiveTime":"2018-10-31 15:43:57.667","receiveTime":"1","ca_AuditState":"4","ca_FangYuanAddress":"省/市-区/县-商圈-诺德-1-1-","ci_State":"10","ci_AddTime":"2018-10-31 14:45:47.0"}
         * baseInformation : {"ci_ClientName":"思创乐高","ci_DesignerCard":"01011085","ci_SalesmanName":"电商","ca_SWIndustryTypeID":"商业店铺","ci_ConstructionState":"3","ci_APP":"1","ci_Type":"商业","ca_HangYeLeIXing":"","tb_diqu":"R6","tb_diquId":11,"ca_LeaseTermEnd":"-","ca_LeaseTermStart":"-","ci_OfficeAddress":"","ci_TypeLevelThird":"","ci_SalesmanCard":"02200100","ci_TypeID":"3","ci_DesignerName":"王秀","ci_DesignState":"0","ci_DesignerSex":"女"}
         * propertyInformation : {"ca_DesignatedFireCompany":"是","ca_FireProtectionGrade":"","ca_TwoManagerTel":"53033899","ca_Aptitude":"二级","ca_Fence":"","ca_property_management_company":"","ca_ProtectiveMaterial":"12mm石膏板","ca_Elevator":"免费","ca_DrawAudit":"有","ca_DesignatedAirCompany":"是","ca_ProductProtection":"是","ca_HtRiskPrice":"0","ca_FireUnitName":"","ca_PropertyCost":"0","ca_Blueprint":"蓝图","ca_PropertyInsurance":"是","ca_InsuranceCompanyName":"","ca_LeadName":"李经理","ca_GarbageTransport":"","ca_HtBlowdownPrice":"0","ca_InsuranceCompany":"否","ca_EnvirProtectionGrade":"","ca_SpecialRequirement":"无","ca_ReqConTime":"晚上","ca_Maintenance":"无","ca_AirUnitName":""}
         * companyInformation : {"ca_EnterpriseNature":"私企","ca_EstablishedYear":"","ca_ForeignEnterprises":"否"}
         * buildingInformation : {"ca_RealEstate":"诺德","ca_DevelopmentFloor":"1","ca_RealEstateId":"","ca_HouseNumber":"","ca_RealEstatePeriod":"1","ca_FloorHeight":""}
         * customerInformation : {"ci_proAge":"30岁-40岁","ca_Authority":"","ca_proHeadIdentity":"老板","ci_proHeadTel":"","ca_NoteFocus":"价格","ca_proHeadCharacter":"","ci_proSex":"女","ci_proHead":"刘女士","ca_Sincerity":"","ca_ContractPerson":"否","ca_TakingRole":"","ci_proCharacteristic":"文艺范"}
         * measureInformation : {"ca_windowsillHight":"200","ca_TuyereMinimumHeight":"3000","ca_MinimumSprayHeight":"3100","ca_WindowType":"落地窗","ca_SpaceMinHeight":"0.00","ca_OccupyPublicCorridor":"否","ca_UpWaterSpot":"1","ca_DownWaterSpot":"1","ca_OriginalTopMaterial":"水泥毛坯","ca_WaterPath":"0","ca_CargoDoorHight":"0","ca_isGroundSmooth":"否","ca_WindowHight":"4000","ca_DownWaterSpotSize":"15.00","ca_WindowWidth":"1200.00","ca_StrongBoxNum":"1","ca_OriginalGroundMaterial":"水泥毛坯","ca_BuildingStructure":"","dataPercentNew":"82.72","ca_SecondaryHeight":"3200","ca_mainBeamHeight":"3500","ca_SpaceMaxHeight":"4000","ca_OriginalGround":"是","ca_OriginalWallMaterial":"","ca_HouseOrientation":"东","ca_WeakBoxNum":"0","ca_CargoDoorWide":"0","ca_CurtainWallSpacing":"1200.00","ca_GroundElevation":"15"}
         * decorateInformation : {"ca_SpecificOpeningTime":"1900-01-01 00:00:00.000","ca_InviteTenders":"否","ca_swRemarks":"小孩玩具店,物业要求施工二级资质,使用面积150平,毛坯,已租,问预算没有说,需求很简单,就是基础的装修,没有太多的设计成份.我给报价600至1000不等.加微信客户给发位置","ca_IndustryPositioning":"单店","ca_PlannedOpeningTime":"","ca_Competitors":"0","ca_DesignatedBrand":"","ca_SpecificDecoration":"具体需求","ca_DecorationDate":"-","ca_proAttribute":"正常单","ci_DecorationAddress":"省/市-区/县-商圈-诺德-1-1-","ca_PlanInvest":"","ca_IntelligentWeakCurrent":"无需求","ca_DesignFeeScope":"","ca_SpaceRequirement":"卫生间，储物间","ca_FireDemand":"","ca_DecBudgetPrice":"10.00","ca_IntentionalStyle":"现代简约","ca_DesignFee":"0","ca_HtWorkCycle":"","ca_Aim":"","ca_FengShuiRequirements":"否","ci_advancePayment":"否","ca_AirConditioningDemand":"","ca_PlannedStartTime":"","ca_SoftFurniture":"无需求","ca_SpecificStartTime":"2019-06-30 13:57:17.810"}
         * housingResourcesInformation : {"ca_Money":"0.00","ca_AvailabilityStatus":"已定","ca_RentFreeDate":"","ca_HousingAddress":"","ci_Area":"150.00","ca_MeasureDate":"1900-01-01","ca_LeaseTerm":"","ca_HousingType":"局部改造","ca_TransactionType":"租","ca_HousingProvince":"","ca_Rents":"","ca_HousingCity":"","ca_LaunchTime":"1900-01-01","ca_HousingTradingArea":"","ci_BuildingArea":"160.00"}
         */

        private OrtherInfomationPojoBean ortherInfomationPojo;
        private BaseInformationBean baseInformation;
        private PropertyInformationBean propertyInformation;
        private CompanyInformationBean companyInformation;
        private BuildingInformationBean buildingInformation;
        private CustomerInformationBean customerInformation;
        private MeasureInformationBean measureInformation;
        private DecorateInformationBean decorateInformation;
        private HousingResourcesInformationBean housingResourcesInformation;

        public OrtherInfomationPojoBean getOrtherInfomationPojo() {
            return ortherInfomationPojo;
        }

        public void setOrtherInfomationPojo(OrtherInfomationPojoBean ortherInfomationPojo) {
            this.ortherInfomationPojo = ortherInfomationPojo;
        }

        public BaseInformationBean getBaseInformation() {
            return baseInformation;
        }

        public void setBaseInformation(BaseInformationBean baseInformation) {
            this.baseInformation = baseInformation;
        }

        public PropertyInformationBean getPropertyInformation() {
            return propertyInformation;
        }

        public void setPropertyInformation(PropertyInformationBean propertyInformation) {
            this.propertyInformation = propertyInformation;
        }

        public CompanyInformationBean getCompanyInformation() {
            return companyInformation;
        }

        public void setCompanyInformation(CompanyInformationBean companyInformation) {
            this.companyInformation = companyInformation;
        }

        public BuildingInformationBean getBuildingInformation() {
            return buildingInformation;
        }

        public void setBuildingInformation(BuildingInformationBean buildingInformation) {
            this.buildingInformation = buildingInformation;
        }

        public CustomerInformationBean getCustomerInformation() {
            return customerInformation;
        }

        public void setCustomerInformation(CustomerInformationBean customerInformation) {
            this.customerInformation = customerInformation;
        }

        public MeasureInformationBean getMeasureInformation() {
            return measureInformation;
        }

        public void setMeasureInformation(MeasureInformationBean measureInformation) {
            this.measureInformation = measureInformation;
        }

        public DecorateInformationBean getDecorateInformation() {
            return decorateInformation;
        }

        public void setDecorateInformation(DecorateInformationBean decorateInformation) {
            this.decorateInformation = decorateInformation;
        }

        public HousingResourcesInformationBean getHousingResourcesInformation() {
            return housingResourcesInformation;
        }

        public void setHousingResourcesInformation(HousingResourcesInformationBean housingResourcesInformation) {
            this.housingResourcesInformation = housingResourcesInformation;
        }

        public static class OrtherInfomationPojoBean {
            /**
             * ci_ElectronicCommerce : 4
             * ci_clientType : 0
             * ci_Stage : 6
             * ci_TurnStatus : 0
             * ci_proRequirement :
             * ci_LFMoney : 0.00
             * mobile : 18510172455
             * ca_AuditTime : 2018-11-01 17:38:08.133
             * ca_SjsSubTime : 2018-11-16 14:42:57.663
             * ci_ReciveStatus : 1
             * ci_ReceiveTime : 2018-10-31 15:43:57.667
             * receiveTime : 1
             * ca_AuditState : 4
             * ca_FangYuanAddress : 省/市-区/县-商圈-诺德-1-1-
             * ci_State : 10
             * ci_AddTime : 2018-10-31 14:45:47.0
             */

            private String ci_ElectronicCommerce;
            private String ci_clientType;
            private String ci_Stage;
            private String ci_TurnStatus;
            private String ci_proRequirement;
            private String ci_LFMoney;
            private String mobile;
            private String ca_AuditTime;
            private String ca_SjsSubTime;
            private String ci_ReciveStatus;
            private String ci_ReceiveTime;
            private String receiveTime;
            private String ca_AuditState;
            private String ca_FangYuanAddress;
            private String ci_State;
            private String ci_AddTime;

            public String getCi_ElectronicCommerce() {
                return ci_ElectronicCommerce;
            }

            public void setCi_ElectronicCommerce(String ci_ElectronicCommerce) {
                this.ci_ElectronicCommerce = ci_ElectronicCommerce;
            }

            public String getCi_clientType() {
                return ci_clientType;
            }

            public void setCi_clientType(String ci_clientType) {
                this.ci_clientType = ci_clientType;
            }

            public String getCi_Stage() {
                return ci_Stage;
            }

            public void setCi_Stage(String ci_Stage) {
                this.ci_Stage = ci_Stage;
            }

            public String getCi_TurnStatus() {
                return ci_TurnStatus;
            }

            public void setCi_TurnStatus(String ci_TurnStatus) {
                this.ci_TurnStatus = ci_TurnStatus;
            }

            public String getCi_proRequirement() {
                return ci_proRequirement;
            }

            public void setCi_proRequirement(String ci_proRequirement) {
                this.ci_proRequirement = ci_proRequirement;
            }

            public String getCi_LFMoney() {
                return ci_LFMoney;
            }

            public void setCi_LFMoney(String ci_LFMoney) {
                this.ci_LFMoney = ci_LFMoney;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getCa_AuditTime() {
                return ca_AuditTime;
            }

            public void setCa_AuditTime(String ca_AuditTime) {
                this.ca_AuditTime = ca_AuditTime;
            }

            public String getCa_SjsSubTime() {
                return ca_SjsSubTime;
            }

            public void setCa_SjsSubTime(String ca_SjsSubTime) {
                this.ca_SjsSubTime = ca_SjsSubTime;
            }

            public String getCi_ReciveStatus() {
                return ci_ReciveStatus;
            }

            public void setCi_ReciveStatus(String ci_ReciveStatus) {
                this.ci_ReciveStatus = ci_ReciveStatus;
            }

            public String getCi_ReceiveTime() {
                return ci_ReceiveTime;
            }

            public void setCi_ReceiveTime(String ci_ReceiveTime) {
                this.ci_ReceiveTime = ci_ReceiveTime;
            }

            public String getReceiveTime() {
                return receiveTime;
            }

            public void setReceiveTime(String receiveTime) {
                this.receiveTime = receiveTime;
            }

            public String getCa_AuditState() {
                return ca_AuditState;
            }

            public void setCa_AuditState(String ca_AuditState) {
                this.ca_AuditState = ca_AuditState;
            }

            public String getCa_FangYuanAddress() {
                return ca_FangYuanAddress;
            }

            public void setCa_FangYuanAddress(String ca_FangYuanAddress) {
                this.ca_FangYuanAddress = ca_FangYuanAddress;
            }

            public String getCi_State() {
                return ci_State;
            }

            public void setCi_State(String ci_State) {
                this.ci_State = ci_State;
            }

            public String getCi_AddTime() {
                return ci_AddTime;
            }

            public void setCi_AddTime(String ci_AddTime) {
                this.ci_AddTime = ci_AddTime;
            }
        }

        public static class BaseInformationBean {
            /**
             * ci_ClientName : 思创乐高
             * ci_DesignerCard : 01011085
             * ci_SalesmanName : 电商
             * ca_SWIndustryTypeID : 商业店铺
             * ci_ConstructionState : 3
             * ci_APP : 1
             * ci_Type : 商业
             * ca_HangYeLeIXing :
             * tb_diqu : R6
             * tb_diquId : 11
             * ca_LeaseTermEnd : -
             * ca_LeaseTermStart : -
             * ci_OfficeAddress :
             * ci_TypeLevelThird :
             * ci_SalesmanCard : 02200100
             * ci_TypeID : 3
             * ci_DesignerName : 王秀
             * ci_DesignState : 0
             * ci_DesignerSex : 女
             */

            private String ci_ClientName;
            private String ci_DesignerCard;
            private String ci_SalesmanName;
            private String ca_SWIndustryTypeID;
            private String ci_ConstructionState;
            private String ci_APP;
            private String ci_Type;
            private String ca_HangYeLeIXing;
            private String tb_diqu;
            private int tb_diquId;
            private String ca_LeaseTermEnd;
            private String ca_LeaseTermStart;
            private String ci_OfficeAddress;
            private String ci_TypeLevelThird;
            private String ci_SalesmanCard;
            private String ci_TypeID;
            private String ci_DesignerName;
            private String ci_DesignState;
            private String ci_DesignerSex;

            public String getCi_ClientName() {
                return ci_ClientName;
            }

            public void setCi_ClientName(String ci_ClientName) {
                this.ci_ClientName = ci_ClientName;
            }

            public String getCi_DesignerCard() {
                return ci_DesignerCard;
            }

            public void setCi_DesignerCard(String ci_DesignerCard) {
                this.ci_DesignerCard = ci_DesignerCard;
            }

            public String getCi_SalesmanName() {
                return ci_SalesmanName;
            }

            public void setCi_SalesmanName(String ci_SalesmanName) {
                this.ci_SalesmanName = ci_SalesmanName;
            }

            public String getCa_SWIndustryTypeID() {
                return ca_SWIndustryTypeID;
            }

            public void setCa_SWIndustryTypeID(String ca_SWIndustryTypeID) {
                this.ca_SWIndustryTypeID = ca_SWIndustryTypeID;
            }

            public String getCi_ConstructionState() {
                return ci_ConstructionState;
            }

            public void setCi_ConstructionState(String ci_ConstructionState) {
                this.ci_ConstructionState = ci_ConstructionState;
            }

            public String getCi_APP() {
                return ci_APP;
            }

            public void setCi_APP(String ci_APP) {
                this.ci_APP = ci_APP;
            }

            public String getCi_Type() {
                return ci_Type;
            }

            public void setCi_Type(String ci_Type) {
                this.ci_Type = ci_Type;
            }

            public String getCa_HangYeLeIXing() {
                return ca_HangYeLeIXing;
            }

            public void setCa_HangYeLeIXing(String ca_HangYeLeIXing) {
                this.ca_HangYeLeIXing = ca_HangYeLeIXing;
            }

            public String getTb_diqu() {
                return tb_diqu;
            }

            public void setTb_diqu(String tb_diqu) {
                this.tb_diqu = tb_diqu;
            }

            public int getTb_diquId() {
                return tb_diquId;
            }

            public void setTb_diquId(int tb_diquId) {
                this.tb_diquId = tb_diquId;
            }

            public String getCa_LeaseTermEnd() {
                return ca_LeaseTermEnd;
            }

            public void setCa_LeaseTermEnd(String ca_LeaseTermEnd) {
                this.ca_LeaseTermEnd = ca_LeaseTermEnd;
            }

            public String getCa_LeaseTermStart() {
                return ca_LeaseTermStart;
            }

            public void setCa_LeaseTermStart(String ca_LeaseTermStart) {
                this.ca_LeaseTermStart = ca_LeaseTermStart;
            }

            public String getCi_OfficeAddress() {
                return ci_OfficeAddress;
            }

            public void setCi_OfficeAddress(String ci_OfficeAddress) {
                this.ci_OfficeAddress = ci_OfficeAddress;
            }

            public String getCi_TypeLevelThird() {
                return ci_TypeLevelThird;
            }

            public void setCi_TypeLevelThird(String ci_TypeLevelThird) {
                this.ci_TypeLevelThird = ci_TypeLevelThird;
            }

            public String getCi_SalesmanCard() {
                return ci_SalesmanCard;
            }

            public void setCi_SalesmanCard(String ci_SalesmanCard) {
                this.ci_SalesmanCard = ci_SalesmanCard;
            }

            public String getCi_TypeID() {
                return ci_TypeID;
            }

            public void setCi_TypeID(String ci_TypeID) {
                this.ci_TypeID = ci_TypeID;
            }

            public String getCi_DesignerName() {
                return ci_DesignerName;
            }

            public void setCi_DesignerName(String ci_DesignerName) {
                this.ci_DesignerName = ci_DesignerName;
            }

            public String getCi_DesignState() {
                return ci_DesignState;
            }

            public void setCi_DesignState(String ci_DesignState) {
                this.ci_DesignState = ci_DesignState;
            }

            public String getCi_DesignerSex() {
                return ci_DesignerSex;
            }

            public void setCi_DesignerSex(String ci_DesignerSex) {
                this.ci_DesignerSex = ci_DesignerSex;
            }
        }

        public static class PropertyInformationBean {
            /**
             * ca_DesignatedFireCompany : 是
             * ca_FireProtectionGrade :
             * ca_TwoManagerTel : 53033899
             * ca_Aptitude : 二级
             * ca_Fence :
             * ca_property_management_company :
             * ca_ProtectiveMaterial : 12mm石膏板
             * ca_Elevator : 免费
             * ca_DrawAudit : 有
             * ca_DesignatedAirCompany : 是
             * ca_ProductProtection : 是
             * ca_HtRiskPrice : 0
             * ca_FireUnitName :
             * ca_PropertyCost : 0
             * ca_Blueprint : 蓝图
             * ca_PropertyInsurance : 是
             * ca_InsuranceCompanyName :
             * ca_LeadName : 李经理
             * ca_GarbageTransport :
             * ca_HtBlowdownPrice : 0
             * ca_InsuranceCompany : 否
             * ca_EnvirProtectionGrade :
             * ca_SpecialRequirement : 无
             * ca_ReqConTime : 晚上
             * ca_Maintenance : 无
             * ca_AirUnitName :
             */

            private String ca_DesignatedFireCompany;
            private String ca_FireProtectionGrade;
            private String ca_TwoManagerTel;
            private String ca_Aptitude;
            private String ca_Fence;
            private String ca_property_management_company;
            private String ca_ProtectiveMaterial;
            private String ca_Elevator;
            private String ca_DrawAudit;
            private String ca_DesignatedAirCompany;
            private String ca_ProductProtection;
            private String ca_HtRiskPrice;
            private String ca_FireUnitName;
            private String ca_PropertyCost;
            private String ca_Blueprint;
            private String ca_PropertyInsurance;
            private String ca_InsuranceCompanyName;
            private String ca_LeadName;
            private String ca_GarbageTransport;
            private String ca_HtBlowdownPrice;
            private String ca_InsuranceCompany;
            private String ca_EnvirProtectionGrade;
            private String ca_SpecialRequirement;
            private String ca_ReqConTime;
            private String ca_Maintenance;
            private String ca_AirUnitName;
            private String ca_PropertyManagerCost;
            private String ca_HydropowerCost;

            public String getCa_HydropowerCost(){
                return ca_HydropowerCost;
            }

            public void setCa_HydropowerCost(String ca_HydropowerCost){
                this.ca_HydropowerCost = ca_HydropowerCost;
            }

            public String getCa_PropertyManagerCost(){
                return  ca_PropertyManagerCost;
            }

            public void setCa_PropertyManagerCost(String ca_propertyManagerCost){
                this.ca_PropertyManagerCost = ca_propertyManagerCost;
            }

            public String getCa_DesignatedFireCompany() {
                return ca_DesignatedFireCompany;
            }

            public void setCa_DesignatedFireCompany(String ca_DesignatedFireCompany) {
                this.ca_DesignatedFireCompany = ca_DesignatedFireCompany;
            }

            public String getCa_FireProtectionGrade() {
                return ca_FireProtectionGrade;
            }

            public void setCa_FireProtectionGrade(String ca_FireProtectionGrade) {
                this.ca_FireProtectionGrade = ca_FireProtectionGrade;
            }

            public String getCa_TwoManagerTel() {
                return ca_TwoManagerTel;
            }

            public void setCa_TwoManagerTel(String ca_TwoManagerTel) {
                this.ca_TwoManagerTel = ca_TwoManagerTel;
            }

            public String getCa_Aptitude() {
                return ca_Aptitude;
            }

            public void setCa_Aptitude(String ca_Aptitude) {
                this.ca_Aptitude = ca_Aptitude;
            }

            public String getCa_Fence() {
                return ca_Fence;
            }

            public void setCa_Fence(String ca_Fence) {
                this.ca_Fence = ca_Fence;
            }

            public String getCa_property_management_company() {
                return ca_property_management_company;
            }

            public void setCa_property_management_company(String ca_property_management_company) {
                this.ca_property_management_company = ca_property_management_company;
            }

            public String getCa_ProtectiveMaterial() {
                return ca_ProtectiveMaterial;
            }

            public void setCa_ProtectiveMaterial(String ca_ProtectiveMaterial) {
                this.ca_ProtectiveMaterial = ca_ProtectiveMaterial;
            }

            public String getCa_Elevator() {
                return ca_Elevator;
            }

            public void setCa_Elevator(String ca_Elevator) {
                this.ca_Elevator = ca_Elevator;
            }

            public String getCa_DrawAudit() {
                return ca_DrawAudit;
            }

            public void setCa_DrawAudit(String ca_DrawAudit) {
                this.ca_DrawAudit = ca_DrawAudit;
            }

            public String getCa_DesignatedAirCompany() {
                return ca_DesignatedAirCompany;
            }

            public void setCa_DesignatedAirCompany(String ca_DesignatedAirCompany) {
                this.ca_DesignatedAirCompany = ca_DesignatedAirCompany;
            }

            public String getCa_ProductProtection() {
                return ca_ProductProtection;
            }

            public void setCa_ProductProtection(String ca_ProductProtection) {
                this.ca_ProductProtection = ca_ProductProtection;
            }

            public String getCa_HtRiskPrice() {
                return ca_HtRiskPrice;
            }

            public void setCa_HtRiskPrice(String ca_HtRiskPrice) {
                this.ca_HtRiskPrice = ca_HtRiskPrice;
            }

            public String getCa_FireUnitName() {
                return ca_FireUnitName;
            }

            public void setCa_FireUnitName(String ca_FireUnitName) {
                this.ca_FireUnitName = ca_FireUnitName;
            }

            public String getCa_PropertyCost() {
                return ca_PropertyCost;
            }

            public void setCa_PropertyCost(String ca_PropertyCost) {
                this.ca_PropertyCost = ca_PropertyCost;
            }

            public String getCa_Blueprint() {
                return ca_Blueprint;
            }

            public void setCa_Blueprint(String ca_Blueprint) {
                this.ca_Blueprint = ca_Blueprint;
            }

            public String getCa_PropertyInsurance() {
                return ca_PropertyInsurance;
            }

            public void setCa_PropertyInsurance(String ca_PropertyInsurance) {
                this.ca_PropertyInsurance = ca_PropertyInsurance;
            }

            public String getCa_InsuranceCompanyName() {
                return ca_InsuranceCompanyName;
            }

            public void setCa_InsuranceCompanyName(String ca_InsuranceCompanyName) {
                this.ca_InsuranceCompanyName = ca_InsuranceCompanyName;
            }

            public String getCa_LeadName() {
                return ca_LeadName;
            }

            public void setCa_LeadName(String ca_LeadName) {
                this.ca_LeadName = ca_LeadName;
            }

            public String getCa_GarbageTransport() {
                return ca_GarbageTransport;
            }

            public void setCa_GarbageTransport(String ca_GarbageTransport) {
                this.ca_GarbageTransport = ca_GarbageTransport;
            }

            public String getCa_HtBlowdownPrice() {
                return ca_HtBlowdownPrice;
            }

            public void setCa_HtBlowdownPrice(String ca_HtBlowdownPrice) {
                this.ca_HtBlowdownPrice = ca_HtBlowdownPrice;
            }

            public String getCa_InsuranceCompany() {
                return ca_InsuranceCompany;
            }

            public void setCa_InsuranceCompany(String ca_InsuranceCompany) {
                this.ca_InsuranceCompany = ca_InsuranceCompany;
            }

            public String getCa_EnvirProtectionGrade() {
                return ca_EnvirProtectionGrade;
            }

            public void setCa_EnvirProtectionGrade(String ca_EnvirProtectionGrade) {
                this.ca_EnvirProtectionGrade = ca_EnvirProtectionGrade;
            }

            public String getCa_SpecialRequirement() {
                return ca_SpecialRequirement;
            }

            public void setCa_SpecialRequirement(String ca_SpecialRequirement) {
                this.ca_SpecialRequirement = ca_SpecialRequirement;
            }

            public String getCa_ReqConTime() {
                return ca_ReqConTime;
            }

            public void setCa_ReqConTime(String ca_ReqConTime) {
                this.ca_ReqConTime = ca_ReqConTime;
            }

            public String getCa_Maintenance() {
                return ca_Maintenance;
            }

            public void setCa_Maintenance(String ca_Maintenance) {
                this.ca_Maintenance = ca_Maintenance;
            }

            public String getCa_AirUnitName() {
                return ca_AirUnitName;
            }

            public void setCa_AirUnitName(String ca_AirUnitName) {
                this.ca_AirUnitName = ca_AirUnitName;
            }
        }

        public static class CompanyInformationBean {
            /**
             * ca_EnterpriseNature : 私企
             * ca_EstablishedYear :
             * ca_ForeignEnterprises : 否
             */

            private String ca_EnterpriseNature;
            private String ca_EstablishedYear;
            private String ca_ForeignEnterprises;

            public String getCa_EnterpriseNature() {
                return ca_EnterpriseNature;
            }

            public void setCa_EnterpriseNature(String ca_EnterpriseNature) {
                this.ca_EnterpriseNature = ca_EnterpriseNature;
            }

            public String getCa_EstablishedYear() {
                return ca_EstablishedYear;
            }

            public void setCa_EstablishedYear(String ca_EstablishedYear) {
                this.ca_EstablishedYear = ca_EstablishedYear;
            }

            public String getCa_ForeignEnterprises() {
                return ca_ForeignEnterprises;
            }

            public void setCa_ForeignEnterprises(String ca_ForeignEnterprises) {
                this.ca_ForeignEnterprises = ca_ForeignEnterprises;
            }
        }

        public static class BuildingInformationBean {
            /**
             * ca_RealEstate : 诺德
             * ca_DevelopmentFloor : 1
             * ca_RealEstateId :
             * ca_HouseNumber :
             * ca_RealEstatePeriod : 1
             * ca_FloorHeight :
             */

            private String ca_RealEstate;
            private String ca_DevelopmentFloor;
            private String ca_RealEstateId;
            private String ca_HouseNumber;
            private String ca_RealEstatePeriod;
            private String ca_FloorHeight;

            public String getCa_RealEstate() {
                return ca_RealEstate;
            }

            public void setCa_RealEstate(String ca_RealEstate) {
                this.ca_RealEstate = ca_RealEstate;
            }

            public String getCa_DevelopmentFloor() {
                return ca_DevelopmentFloor;
            }

            public void setCa_DevelopmentFloor(String ca_DevelopmentFloor) {
                this.ca_DevelopmentFloor = ca_DevelopmentFloor;
            }

            public String getCa_RealEstateId() {
                return ca_RealEstateId;
            }

            public void setCa_RealEstateId(String ca_RealEstateId) {
                this.ca_RealEstateId = ca_RealEstateId;
            }

            public String getCa_HouseNumber() {
                return ca_HouseNumber;
            }

            public void setCa_HouseNumber(String ca_HouseNumber) {
                this.ca_HouseNumber = ca_HouseNumber;
            }

            public String getCa_RealEstatePeriod() {
                return ca_RealEstatePeriod;
            }

            public void setCa_RealEstatePeriod(String ca_RealEstatePeriod) {
                this.ca_RealEstatePeriod = ca_RealEstatePeriod;
            }

            public String getCa_FloorHeight() {
                return ca_FloorHeight;
            }

            public void setCa_FloorHeight(String ca_FloorHeight) {
                this.ca_FloorHeight = ca_FloorHeight;
            }
        }

        public static class CustomerInformationBean {
            /**
             * ci_proAge : 30岁-40岁
             * ca_Authority :
             * ca_proHeadIdentity : 老板
             * ci_proHeadTel :
             * ca_NoteFocus : 价格
             * ca_proHeadCharacter :
             * ci_proSex : 女
             * ci_proHead : 刘女士
             * ca_Sincerity :
             * ca_ContractPerson : 否
             * ca_TakingRole :
             * ci_proCharacteristic : 文艺范
             */

            private String ci_proAge;
            private String ca_Authority;
            private String ca_proHeadIdentity;
            private String ci_proHeadTel;
            private String ca_NoteFocus;
            private String ca_proHeadCharacter;
            private String ci_proSex;
            private String ci_proHead;
            private String ca_Sincerity;
            private String ca_ContractPerson;
            private String ca_TakingRole;
            private String ci_proCharacteristic;

            public String getCi_proAge() {
                return ci_proAge;
            }

            public void setCi_proAge(String ci_proAge) {
                this.ci_proAge = ci_proAge;
            }

            public String getCa_Authority() {
                return ca_Authority;
            }

            public void setCa_Authority(String ca_Authority) {
                this.ca_Authority = ca_Authority;
            }

            public String getCa_proHeadIdentity() {
                return ca_proHeadIdentity;
            }

            public void setCa_proHeadIdentity(String ca_proHeadIdentity) {
                this.ca_proHeadIdentity = ca_proHeadIdentity;
            }

            public String getCi_proHeadTel() {
                return ci_proHeadTel;
            }

            public void setCi_proHeadTel(String ci_proHeadTel) {
                this.ci_proHeadTel = ci_proHeadTel;
            }

            public String getCa_NoteFocus() {
                return ca_NoteFocus;
            }

            public void setCa_NoteFocus(String ca_NoteFocus) {
                this.ca_NoteFocus = ca_NoteFocus;
            }

            public String getCa_proHeadCharacter() {
                return ca_proHeadCharacter;
            }

            public void setCa_proHeadCharacter(String ca_proHeadCharacter) {
                this.ca_proHeadCharacter = ca_proHeadCharacter;
            }

            public String getCi_proSex() {
                return ci_proSex;
            }

            public void setCi_proSex(String ci_proSex) {
                this.ci_proSex = ci_proSex;
            }

            public String getCi_proHead() {
                return ci_proHead;
            }

            public void setCi_proHead(String ci_proHead) {
                this.ci_proHead = ci_proHead;
            }

            public String getCa_Sincerity() {
                return ca_Sincerity;
            }

            public void setCa_Sincerity(String ca_Sincerity) {
                this.ca_Sincerity = ca_Sincerity;
            }

            public String getCa_ContractPerson() {
                return ca_ContractPerson;
            }

            public void setCa_ContractPerson(String ca_ContractPerson) {
                this.ca_ContractPerson = ca_ContractPerson;
            }

            public String getCa_TakingRole() {
                return ca_TakingRole;
            }

            public void setCa_TakingRole(String ca_TakingRole) {
                this.ca_TakingRole = ca_TakingRole;
            }

            public String getCi_proCharacteristic() {
                return ci_proCharacteristic;
            }

            public void setCi_proCharacteristic(String ci_proCharacteristic) {
                this.ci_proCharacteristic = ci_proCharacteristic;
            }
        }

        public static class MeasureInformationBean {
            /**
             * ca_windowsillHight : 200
             * ca_TuyereMinimumHeight : 3000
             * ca_MinimumSprayHeight : 3100
             * ca_WindowType : 落地窗
             * ca_SpaceMinHeight : 0.00
             * ca_OccupyPublicCorridor : 否
             * ca_UpWaterSpot : 1
             * ca_DownWaterSpot : 1
             * ca_OriginalTopMaterial : 水泥毛坯
             * ca_WaterPath : 0
             * ca_CargoDoorHight : 0
             * ca_isGroundSmooth : 否
             * ca_WindowHight : 4000
             * ca_DownWaterSpotSize : 15.00
             * ca_WindowWidth : 1200.00
             * ca_StrongBoxNum : 1
             * ca_OriginalGroundMaterial : 水泥毛坯
             * ca_BuildingStructure :
             * dataPercentNew : 82.72
             * ca_SecondaryHeight : 3200
             * ca_mainBeamHeight : 3500
             * ca_SpaceMaxHeight : 4000
             * ca_OriginalGround : 是
             * ca_OriginalWallMaterial :
             * ca_HouseOrientation : 东
             * ca_WeakBoxNum : 0
             * ca_CargoDoorWide : 0
             * ca_CurtainWallSpacing : 1200.00
             * ca_GroundElevation : 15
             */

            private String ca_windowsillHight;
            private String ca_TuyereMinimumHeight;
            private String ca_MinimumSprayHeight;
            private String ca_WindowType;
            private String ca_SpaceMinHeight;
            private String ca_OccupyPublicCorridor;
            private String ca_UpWaterSpot;
            private String ca_DownWaterSpot;
            private String ca_OriginalTopMaterial;
            private String ca_WaterPath;
            private String ca_CargoDoorHight;
            private String ca_isGroundSmooth;
            private String ca_WindowHight;
            private String ca_DownWaterSpotSize;
            private String ca_WindowWidth;
            private String ca_StrongBoxNum;
            private String ca_OriginalGroundMaterial;
            private String ca_BuildingStructure;
            private String dataPercentNew;
            private String ca_SecondaryHeight;
            private String ca_mainBeamHeight;
            private String ca_SpaceMaxHeight;
            private String ca_OriginalGround;
            private String ca_OriginalWallMaterial;
            private String ca_HouseOrientation;
            private String ca_WeakBoxNum;
            private String ca_CargoDoorWide;
            private String ca_CurtainWallSpacing;
            private String ca_GroundElevation;

            public String getCa_windowsillHight() {
                return ca_windowsillHight;
            }

            public void setCa_windowsillHight(String ca_windowsillHight) {
                this.ca_windowsillHight = ca_windowsillHight;
            }

            public String getCa_TuyereMinimumHeight() {
                return ca_TuyereMinimumHeight;
            }

            public void setCa_TuyereMinimumHeight(String ca_TuyereMinimumHeight) {
                this.ca_TuyereMinimumHeight = ca_TuyereMinimumHeight;
            }

            public String getCa_MinimumSprayHeight() {
                return ca_MinimumSprayHeight;
            }

            public void setCa_MinimumSprayHeight(String ca_MinimumSprayHeight) {
                this.ca_MinimumSprayHeight = ca_MinimumSprayHeight;
            }

            public String getCa_WindowType() {
                return ca_WindowType;
            }

            public void setCa_WindowType(String ca_WindowType) {
                this.ca_WindowType = ca_WindowType;
            }

            public String getCa_SpaceMinHeight() {
                return ca_SpaceMinHeight;
            }

            public void setCa_SpaceMinHeight(String ca_SpaceMinHeight) {
                this.ca_SpaceMinHeight = ca_SpaceMinHeight;
            }

            public String getCa_OccupyPublicCorridor() {
                return ca_OccupyPublicCorridor;
            }

            public void setCa_OccupyPublicCorridor(String ca_OccupyPublicCorridor) {
                this.ca_OccupyPublicCorridor = ca_OccupyPublicCorridor;
            }

            public String getCa_UpWaterSpot() {
                return ca_UpWaterSpot;
            }

            public void setCa_UpWaterSpot(String ca_UpWaterSpot) {
                this.ca_UpWaterSpot = ca_UpWaterSpot;
            }

            public String getCa_DownWaterSpot() {
                return ca_DownWaterSpot;
            }

            public void setCa_DownWaterSpot(String ca_DownWaterSpot) {
                this.ca_DownWaterSpot = ca_DownWaterSpot;
            }

            public String getCa_OriginalTopMaterial() {
                return ca_OriginalTopMaterial;
            }

            public void setCa_OriginalTopMaterial(String ca_OriginalTopMaterial) {
                this.ca_OriginalTopMaterial = ca_OriginalTopMaterial;
            }

            public String getCa_WaterPath() {
                return ca_WaterPath;
            }

            public void setCa_WaterPath(String ca_WaterPath) {
                this.ca_WaterPath = ca_WaterPath;
            }

            public String getCa_CargoDoorHight() {
                return ca_CargoDoorHight;
            }

            public void setCa_CargoDoorHight(String ca_CargoDoorHight) {
                this.ca_CargoDoorHight = ca_CargoDoorHight;
            }

            public String getCa_isGroundSmooth() {
                return ca_isGroundSmooth;
            }

            public void setCa_isGroundSmooth(String ca_isGroundSmooth) {
                this.ca_isGroundSmooth = ca_isGroundSmooth;
            }

            public String getCa_WindowHight() {
                return ca_WindowHight;
            }

            public void setCa_WindowHight(String ca_WindowHight) {
                this.ca_WindowHight = ca_WindowHight;
            }

            public String getCa_DownWaterSpotSize() {
                return ca_DownWaterSpotSize;
            }

            public void setCa_DownWaterSpotSize(String ca_DownWaterSpotSize) {
                this.ca_DownWaterSpotSize = ca_DownWaterSpotSize;
            }

            public String getCa_WindowWidth() {
                return ca_WindowWidth;
            }

            public void setCa_WindowWidth(String ca_WindowWidth) {
                this.ca_WindowWidth = ca_WindowWidth;
            }

            public String getCa_StrongBoxNum() {
                return ca_StrongBoxNum;
            }

            public void setCa_StrongBoxNum(String ca_StrongBoxNum) {
                this.ca_StrongBoxNum = ca_StrongBoxNum;
            }

            public String getCa_OriginalGroundMaterial() {
                return ca_OriginalGroundMaterial;
            }

            public void setCa_OriginalGroundMaterial(String ca_OriginalGroundMaterial) {
                this.ca_OriginalGroundMaterial = ca_OriginalGroundMaterial;
            }

            public String getCa_BuildingStructure() {
                return ca_BuildingStructure;
            }

            public void setCa_BuildingStructure(String ca_BuildingStructure) {
                this.ca_BuildingStructure = ca_BuildingStructure;
            }

            public String getDataPercentNew() {
                return dataPercentNew;
            }

            public void setDataPercentNew(String dataPercentNew) {
                this.dataPercentNew = dataPercentNew;
            }

            public String getCa_SecondaryHeight() {
                return ca_SecondaryHeight;
            }

            public void setCa_SecondaryHeight(String ca_SecondaryHeight) {
                this.ca_SecondaryHeight = ca_SecondaryHeight;
            }

            public String getCa_mainBeamHeight() {
                return ca_mainBeamHeight;
            }

            public void setCa_mainBeamHeight(String ca_mainBeamHeight) {
                this.ca_mainBeamHeight = ca_mainBeamHeight;
            }

            public String getCa_SpaceMaxHeight() {
                return ca_SpaceMaxHeight;
            }

            public void setCa_SpaceMaxHeight(String ca_SpaceMaxHeight) {
                this.ca_SpaceMaxHeight = ca_SpaceMaxHeight;
            }

            public String getCa_OriginalGround() {
                return ca_OriginalGround;
            }

            public void setCa_OriginalGround(String ca_OriginalGround) {
                this.ca_OriginalGround = ca_OriginalGround;
            }

            public String getCa_OriginalWallMaterial() {
                return ca_OriginalWallMaterial;
            }

            public void setCa_OriginalWallMaterial(String ca_OriginalWallMaterial) {
                this.ca_OriginalWallMaterial = ca_OriginalWallMaterial;
            }

            public String getCa_HouseOrientation() {
                return ca_HouseOrientation;
            }

            public void setCa_HouseOrientation(String ca_HouseOrientation) {
                this.ca_HouseOrientation = ca_HouseOrientation;
            }

            public String getCa_WeakBoxNum() {
                return ca_WeakBoxNum;
            }

            public void setCa_WeakBoxNum(String ca_WeakBoxNum) {
                this.ca_WeakBoxNum = ca_WeakBoxNum;
            }

            public String getCa_CargoDoorWide() {
                return ca_CargoDoorWide;
            }

            public void setCa_CargoDoorWide(String ca_CargoDoorWide) {
                this.ca_CargoDoorWide = ca_CargoDoorWide;
            }

            public String getCa_CurtainWallSpacing() {
                return ca_CurtainWallSpacing;
            }

            public void setCa_CurtainWallSpacing(String ca_CurtainWallSpacing) {
                this.ca_CurtainWallSpacing = ca_CurtainWallSpacing;
            }

            public String getCa_GroundElevation() {
                return ca_GroundElevation;
            }

            public void setCa_GroundElevation(String ca_GroundElevation) {
                this.ca_GroundElevation = ca_GroundElevation;
            }
        }

        public static class DecorateInformationBean {
            /**
             * ca_SpecificOpeningTime : 1900-01-01 00:00:00.000
             * ca_InviteTenders : 否
             * ca_swRemarks : 小孩玩具店,物业要求施工二级资质,使用面积150平,毛坯,已租,问预算没有说,需求很简单,就是基础的装修,没有太多的设计成份.我给报价600至1000不等.加微信客户给发位置
             * ca_IndustryPositioning : 单店
             * ca_PlannedOpeningTime :
             * ca_Competitors : 0
             * ca_DesignatedBrand :
             * ca_SpecificDecoration : 具体需求
             * ca_DecorationDate : -
             * ca_proAttribute : 正常单
             * ci_DecorationAddress : 省/市-区/县-商圈-诺德-1-1-
             * ca_PlanInvest :
             * ca_IntelligentWeakCurrent : 无需求
             * ca_DesignFeeScope :
             * ca_SpaceRequirement : 卫生间，储物间
             * ca_FireDemand :
             * ca_DecBudgetPrice : 10.00
             * ca_IntentionalStyle : 现代简约
             * ca_DesignFee : 0
             * ca_HtWorkCycle :
             * ca_Aim :
             * ca_FengShuiRequirements : 否
             * ci_advancePayment : 否
             * ca_AirConditioningDemand :
             * ca_PlannedStartTime :
             * ca_SoftFurniture : 无需求
             * ca_SpecificStartTime : 2019-06-30 13:57:17.810
             */

            private String ca_SpecificOpeningTime;
            private String ca_InviteTenders;
            private String ca_swRemarks;
            private String ca_IndustryPositioning;
            private String ca_PlannedOpeningTime;
            private String ca_Competitors;
            private String ca_DesignatedBrand;
            private String ca_SpecificDecoration;
            private String ca_DecorationDate;
            private String ca_proAttribute;
            private String ci_DecorationAddress;
            private String ca_PlanInvest;
            private String ca_IntelligentWeakCurrent;
            private String ca_DesignFeeScope;
            private String ca_SpaceRequirement;
            private String ca_FireDemand;
            private String ca_DecBudgetPrice;
            private String ca_IntentionalStyle;
            private String ca_DesignFee;
            private String ca_HtWorkCycle;
            private String ca_Aim;
            private String ca_FengShuiRequirements;
            private String ci_advancePayment;
            private String ca_AirConditioningDemand;
            private String ca_PlannedStartTime;
            private String ca_SoftFurniture;
            private String ca_SpecificStartTime;

            public String getCa_SpecificOpeningTime() {
                return ca_SpecificOpeningTime;
            }

            public void setCa_SpecificOpeningTime(String ca_SpecificOpeningTime) {
                this.ca_SpecificOpeningTime = ca_SpecificOpeningTime;
            }

            public String getCa_InviteTenders() {
                return ca_InviteTenders;
            }

            public void setCa_InviteTenders(String ca_InviteTenders) {
                this.ca_InviteTenders = ca_InviteTenders;
            }

            public String getCa_swRemarks() {
                return ca_swRemarks;
            }

            public void setCa_swRemarks(String ca_swRemarks) {
                this.ca_swRemarks = ca_swRemarks;
            }

            public String getCa_IndustryPositioning() {
                return ca_IndustryPositioning;
            }

            public void setCa_IndustryPositioning(String ca_IndustryPositioning) {
                this.ca_IndustryPositioning = ca_IndustryPositioning;
            }

            public String getCa_PlannedOpeningTime() {
                return ca_PlannedOpeningTime;
            }

            public void setCa_PlannedOpeningTime(String ca_PlannedOpeningTime) {
                this.ca_PlannedOpeningTime = ca_PlannedOpeningTime;
            }

            public String getCa_Competitors() {
                return ca_Competitors;
            }

            public void setCa_Competitors(String ca_Competitors) {
                this.ca_Competitors = ca_Competitors;
            }

            public String getCa_DesignatedBrand() {
                return ca_DesignatedBrand;
            }

            public void setCa_DesignatedBrand(String ca_DesignatedBrand) {
                this.ca_DesignatedBrand = ca_DesignatedBrand;
            }

            public String getCa_SpecificDecoration() {
                return ca_SpecificDecoration;
            }

            public void setCa_SpecificDecoration(String ca_SpecificDecoration) {
                this.ca_SpecificDecoration = ca_SpecificDecoration;
            }

            public String getCa_DecorationDate() {
                return ca_DecorationDate;
            }

            public void setCa_DecorationDate(String ca_DecorationDate) {
                this.ca_DecorationDate = ca_DecorationDate;
            }

            public String getCa_proAttribute() {
                return ca_proAttribute;
            }

            public void setCa_proAttribute(String ca_proAttribute) {
                this.ca_proAttribute = ca_proAttribute;
            }

            public String getCi_DecorationAddress() {
                return ci_DecorationAddress;
            }

            public void setCi_DecorationAddress(String ci_DecorationAddress) {
                this.ci_DecorationAddress = ci_DecorationAddress;
            }

            public String getCa_PlanInvest() {
                return ca_PlanInvest;
            }

            public void setCa_PlanInvest(String ca_PlanInvest) {
                this.ca_PlanInvest = ca_PlanInvest;
            }

            public String getCa_IntelligentWeakCurrent() {
                return ca_IntelligentWeakCurrent;
            }

            public void setCa_IntelligentWeakCurrent(String ca_IntelligentWeakCurrent) {
                this.ca_IntelligentWeakCurrent = ca_IntelligentWeakCurrent;
            }

            public String getCa_DesignFeeScope() {
                return ca_DesignFeeScope;
            }

            public void setCa_DesignFeeScope(String ca_DesignFeeScope) {
                this.ca_DesignFeeScope = ca_DesignFeeScope;
            }

            public String getCa_SpaceRequirement() {
                return ca_SpaceRequirement;
            }

            public void setCa_SpaceRequirement(String ca_SpaceRequirement) {
                this.ca_SpaceRequirement = ca_SpaceRequirement;
            }

            public String getCa_FireDemand() {
                return ca_FireDemand;
            }

            public void setCa_FireDemand(String ca_FireDemand) {
                this.ca_FireDemand = ca_FireDemand;
            }

            public String getCa_DecBudgetPrice() {
                return ca_DecBudgetPrice;
            }

            public void setCa_DecBudgetPrice(String ca_DecBudgetPrice) {
                this.ca_DecBudgetPrice = ca_DecBudgetPrice;
            }

            public String getCa_IntentionalStyle() {
                return ca_IntentionalStyle;
            }

            public void setCa_IntentionalStyle(String ca_IntentionalStyle) {
                this.ca_IntentionalStyle = ca_IntentionalStyle;
            }

            public String getCa_DesignFee() {
                return ca_DesignFee;
            }

            public void setCa_DesignFee(String ca_DesignFee) {
                this.ca_DesignFee = ca_DesignFee;
            }

            public String getCa_HtWorkCycle() {
                return ca_HtWorkCycle;
            }

            public void setCa_HtWorkCycle(String ca_HtWorkCycle) {
                this.ca_HtWorkCycle = ca_HtWorkCycle;
            }

            public String getCa_Aim() {
                return ca_Aim;
            }

            public void setCa_Aim(String ca_Aim) {
                this.ca_Aim = ca_Aim;
            }

            public String getCa_FengShuiRequirements() {
                return ca_FengShuiRequirements;
            }

            public void setCa_FengShuiRequirements(String ca_FengShuiRequirements) {
                this.ca_FengShuiRequirements = ca_FengShuiRequirements;
            }

            public String getCi_advancePayment() {
                return ci_advancePayment;
            }

            public void setCi_advancePayment(String ci_advancePayment) {
                this.ci_advancePayment = ci_advancePayment;
            }

            public String getCa_AirConditioningDemand() {
                return ca_AirConditioningDemand;
            }

            public void setCa_AirConditioningDemand(String ca_AirConditioningDemand) {
                this.ca_AirConditioningDemand = ca_AirConditioningDemand;
            }

            public String getCa_PlannedStartTime() {
                return ca_PlannedStartTime;
            }

            public void setCa_PlannedStartTime(String ca_PlannedStartTime) {
                this.ca_PlannedStartTime = ca_PlannedStartTime;
            }

            public String getCa_SoftFurniture() {
                return ca_SoftFurniture;
            }

            public void setCa_SoftFurniture(String ca_SoftFurniture) {
                this.ca_SoftFurniture = ca_SoftFurniture;
            }

            public String getCa_SpecificStartTime() {
                return ca_SpecificStartTime;
            }

            public void setCa_SpecificStartTime(String ca_SpecificStartTime) {
                this.ca_SpecificStartTime = ca_SpecificStartTime;
            }
        }

        public static class HousingResourcesInformationBean {
            /**
             * ca_Money : 0.00
             * ca_AvailabilityStatus : 已定
             * ca_RentFreeDate :
             * ca_HousingAddress :
             * ci_Area : 150.00
             * ca_MeasureDate : 1900-01-01
             * ca_LeaseTerm :
             * ca_HousingType : 局部改造
             * ca_TransactionType : 租
             * ca_HousingProvince :
             * ca_Rents :
             * ca_HousingCity :
             * ca_LaunchTime : 1900-01-01
             * ca_HousingTradingArea :
             * ci_BuildingArea : 160.00
             */

            private String ca_Money;
            private String ca_AvailabilityStatus;
            private String ca_RentFreeDate;
            private String ca_HousingAddress;
            private String ci_Area;
            private String ca_MeasureDate;
            private String ca_LeaseTerm;
            private String ca_HousingType;
            private String ca_TransactionType;
            private String ca_HousingProvince;
            private String ca_Rents;
            private String ca_HousingCity;
            private String ca_LaunchTime;
            private String ca_HousingTradingArea;
            private String ci_BuildingArea;

            public String getCa_Money() {
                return ca_Money;
            }

            public void setCa_Money(String ca_Money) {
                this.ca_Money = ca_Money;
            }

            public String getCa_AvailabilityStatus() {
                return ca_AvailabilityStatus;
            }

            public void setCa_AvailabilityStatus(String ca_AvailabilityStatus) {
                this.ca_AvailabilityStatus = ca_AvailabilityStatus;
            }

            public String getCa_RentFreeDate() {
                return ca_RentFreeDate;
            }

            public void setCa_RentFreeDate(String ca_RentFreeDate) {
                this.ca_RentFreeDate = ca_RentFreeDate;
            }

            public String getCa_HousingAddress() {
                return ca_HousingAddress;
            }

            public void setCa_HousingAddress(String ca_HousingAddress) {
                this.ca_HousingAddress = ca_HousingAddress;
            }

            public String getCi_Area() {
                return ci_Area;
            }

            public void setCi_Area(String ci_Area) {
                this.ci_Area = ci_Area;
            }

            public String getCa_MeasureDate() {
                return ca_MeasureDate;
            }

            public void setCa_MeasureDate(String ca_MeasureDate) {
                this.ca_MeasureDate = ca_MeasureDate;
            }

            public String getCa_LeaseTerm() {
                return ca_LeaseTerm;
            }

            public void setCa_LeaseTerm(String ca_LeaseTerm) {
                this.ca_LeaseTerm = ca_LeaseTerm;
            }

            public String getCa_HousingType() {
                return ca_HousingType;
            }

            public void setCa_HousingType(String ca_HousingType) {
                this.ca_HousingType = ca_HousingType;
            }

            public String getCa_TransactionType() {
                return ca_TransactionType;
            }

            public void setCa_TransactionType(String ca_TransactionType) {
                this.ca_TransactionType = ca_TransactionType;
            }

            public String getCa_HousingProvince() {
                return ca_HousingProvince;
            }

            public void setCa_HousingProvince(String ca_HousingProvince) {
                this.ca_HousingProvince = ca_HousingProvince;
            }

            public String getCa_Rents() {
                return ca_Rents;
            }

            public void setCa_Rents(String ca_Rents) {
                this.ca_Rents = ca_Rents;
            }

            public String getCa_HousingCity() {
                return ca_HousingCity;
            }

            public void setCa_HousingCity(String ca_HousingCity) {
                this.ca_HousingCity = ca_HousingCity;
            }

            public String getCa_LaunchTime() {
                return ca_LaunchTime;
            }

            public void setCa_LaunchTime(String ca_LaunchTime) {
                this.ca_LaunchTime = ca_LaunchTime;
            }

            public String getCa_HousingTradingArea() {
                return ca_HousingTradingArea;
            }

            public void setCa_HousingTradingArea(String ca_HousingTradingArea) {
                this.ca_HousingTradingArea = ca_HousingTradingArea;
            }

            public String getCi_BuildingArea() {
                return ci_BuildingArea;
            }

            public void setCi_BuildingArea(String ci_BuildingArea) {
                this.ci_BuildingArea = ci_BuildingArea;
            }
        }
    }
}
