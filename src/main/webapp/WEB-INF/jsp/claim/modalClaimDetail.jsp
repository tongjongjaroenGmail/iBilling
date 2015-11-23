<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="modal fade" id="modalClaimDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="overflow-x: auto; overflow-y: auto;">
		<div class="modal-dialog" style="width: 1200px">
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="modalSaveHeaderLabel">ตรวจสอบเคลม --> รายละเอียด</h5>
				</div>
				<div class="modal-body" style="padding: 5px;">
					<input type="hidden" id="claimId">
					
					<div class="well" style="padding: 5px;margin-bottom: 4px;">
					
						<div class="row">
							<div class="col-sm-12">
								<div class="table-responsive">
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>เลขเคลม : </b> 
										</div>
									</div>
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="claimNo" type="text" maxlength="20" readonly="readonly"/> 
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>วันที่จ่ายงาน : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="dispatchDate" type="text" readonly="readonly"/> 
											<span class="input-group-addon"> 
												<i class="icon-calendar bigger-110"></i>
											</span>
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>วันที่ส่งงาน : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="sendrptDate" type="text" readonly="readonly"/> 
											<span class="input-group-addon"> 
												<i class="icon-calendar bigger-110"></i>
											</span>
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left" style="width: 120px;">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>สถานะส่งงาน : </b> 
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="claimStatus" type="text" readonly="readonly"/> 
										</div>
									</div>
								</div>
							</div>		
						</div>
						
						<div class="space-4"></div>
						
						<div class="row">
							<div class="col-sm-12">
								<div class="table-responsive">
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>พนักงาน : </b> 
										</div>
									</div>
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="employeeCode" type="text" readonly="readonly"/> 
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>ศูนย์ : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="center" type="text" readonly="readonly"/>
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>ประเภทเคลม : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="claimType" type="text" readonly="readonly"/>
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left" style="width: 120px;">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>ประเภทการจ่าย : </b> 
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="dispatchType" type="text" readonly="readonly"/>
										</div>
									</div>
								</div>
							</div>		
						</div>		
								
						<div class="space-4"></div>
					
						<div class="row">
							<div class="col-sm-12">
								<div class="table-responsive">
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>พื้นที่ : </b> 
										</div>
									</div>
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="areaType" type="text" readonly="readonly"/>
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>เวร : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="workTime" type="text" readonly="readonly"/>
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<label><input class="ace" id="coArea" type="checkbox" disabled="disabled"><span class="lbl"><b> นอกพื้นที่</b></span></label>
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<label><input class="ace" id="disperse" type="checkbox" disabled="disabled"><span class="lbl"><b> แยกย้าย</b></span></label>
										</div>
									</div>
	
								</div>
							</div>					
						</div>	
						
						<div class="space-4"></div>
						
						<div class="row">
							<div class="col-sm-12">
								<div class="table-responsive">
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>ประเภทบริการ: </b> 
										</div>
									</div>
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="serviceType" type="text" readonly="readonly"/>
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>อำเภอที่ตรวจสอบ  : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="serviceAmphur" type="text" readonly="readonly"/>
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>จังหวัดที่ตรวจสอบ : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="serviceProvince" type="text" readonly="readonly"/>
										</div>
									</div>
								</div>
							</div>		
						</div>	
						
						<div class="space-4"></div>
						
						
						<div class="row">
							<div class="col-sm-12">
								<div class="table-responsive">
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>จำนวนรูป : </b> 
										</div>
									</div>
									<div class="col-sm-1 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="photoNum" type="text" readonly="readonly" style="text-align: right;"/>
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>จำนวนข้อปจว. : </b> 
										</div>
									</div>
									
									<div class="col-sm-1 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<div class="input-group col-sm-12 no-padding-left no-padding-right">
												<input class="form-control" id="policeRptNum" type="text" readonly="readonly" style="text-align: right;"/>
											</div>
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>เงื่อนไขฝ่ายถูก : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<div class="input-group col-sm-12 no-padding-left no-padding-right">
												<input class="form-control" id="claimTp" type="text" readonly="readonly"/>
											</div>
										</div>
									</div>
									
<!-- 									<div class="col-sm-1 no-padding-left">		 -->
<!-- 										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;"> -->
<!-- 											<b>ค่าเรียกร้อง : </b>  -->
<!-- 										</div> -->
<!-- 									</div> -->
									
<!-- 									<div class="col-sm-2 no-padding-left">	 -->
<!-- 										<div class="input-group col-sm-12 no-padding-left no-padding-right"> -->
<!-- 											<div class="input-group col-sm-12 no-padding-left no-padding-right"> -->
<!-- 												<input class="form-control" id="surClaim" type="text" readonly="readonly"/> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
								</div>
							</div>		
						</div>	
						
						<div class="space-4"></div>
						
						<div class="row">
							<div class="col-sm-12">
								<div class="table-responsive">
									<div class="col-sm-3 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
					
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>ผู้ตรวจสอบ : </b> 
										</div>
									</div>
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="reviewBy" type="text" readonly="readonly"/> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">		
										<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
											<b>วันที่ตรวจงาน : </b> 
										</div>
									</div>
									
									<div class="col-sm-2 no-padding-left">	
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="srSendDate" type="text" readonly="readonly"/> 
											<span class="input-group-addon"> 
												<i class="icon-calendar bigger-110"></i>
											</span>
										</div>
									</div>
								</div>
							</div>		
						</div>	
					
					</div>
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-6">
							
							<div class="row">
								<div class="col-sm-12">
									<div class="table-responsive">
										<div class="col-sm-4">		
											<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
												<b>ยอดวางบิลทิพยฯ</b> 
											</div>
										</div>
										
										<div class="col-sm-3" >		
											<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
												<b>เลขที่วางบิล : </b>
											</div>
										</div>
										
										<div class="col-sm-3">		
											<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
												<input class="form-control" id="invoiceCode" type="text" readonly="readonly"/> 
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="space-4"></div>
							
							<div class="well" style="padding: 5px;margin-bottom: 4px;">
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าบริการที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surInvest" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าพาหนะที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surTrans" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าประจำวันที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surDaily" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่ารูปที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surPhoto" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าเรียกร้องที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surClaim" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าโทรศัพท์ที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surTel" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>		
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าประกันตัวที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surInsure" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่ารถยกที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surTowcar" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ค่าใช้จ่ายอื่นๆที่นำเสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surOther" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ยอดรวมก่อนภาษีที่เสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surTotalNoTax" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ภาษีที่เสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surTax" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
								<div class="space-4"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="table-responsive">
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
													<b>ยอดรวมหลังภาษีที่เสนอ : </b> 
												</div>
											</div>
											<div class="col-sm-4 no-padding-left">		
												<div class="input-group col-sm-12 no-padding-left no-padding-right">
													<input class="form-control" id="surTotalWithTax" type="text" readonly="readonly" style="text-align: right;"/> 
												</div>
											</div>
										</div>
									</div>		
								</div>	
							</div>
						</div>	
						
						<input type="hidden" id="paySurveyId">
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-12">
									<div class="table-responsive">
										<div class="col-sm-4">		
											<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
												<b>ยอดจ่ายค่าสำรวจพนักงาน</b> 
											</div>
										</div>
										
										<div class="col-sm-3" >		
											<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
												<b>เลขที่จ่าย : </b>
											</div>
										</div>
										
										<div class="col-sm-3">		
											<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
												<input class="form-control" id="paySurveyCode" type="text" readonly="readonly"/> 
											</div>
										</div>
									</div>
								</div>
							</div>
								
							<div class="space-4"></div>
							
								<div class="well" style="padding: 5px;margin-bottom: 4px;">
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าบริการ : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyInvest" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าพาหนะ : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyTrans" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าประจำวัน : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyDaily" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่ารูป : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyPhoto" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าเรียกร้อง : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyClaim" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าโทรศัพท์ : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyTel" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าเงื่อนไขฝ่ายถูก : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyConditionRight" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าใช้จ่ายอื่นๆ : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyOther" type="text"  style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ค่าปรับ (-) : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyFine" type="text" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
									<div class="space-4"></div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive">
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
														<b>ยอดรวม : </b> 
													</div>
												</div>
												<div class="col-sm-4 no-padding-left">		
													<div class="input-group col-sm-12 no-padding-left no-padding-right">
														<input class="form-control" id="surveyTotal" type="text" readonly="readonly" style="text-align: right;"/> 
													</div>
												</div>
											</div>
										</div>		
									</div>	
								</div>
						
							<div class="space-4"></div>
						</div>		
					</div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>หมายเหตุ : </b> 
									</div>
								</div>
								<div class="col-sm-4 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<textarea rows="3" cols="50" id="remark"></textarea>
									</div>
								</div>
								
								<div class="col-sm-1 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ผู้บันทึก : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="createBy" type="text" readonly="readonly"/> 
									</div>
								</div>
								
								<div class="col-sm-1 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันที่บันทึก : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="createDate" type="text" readonly="readonly"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
							</div>
						</div>		
					</div>	

					<div class="modal-footer">
						<button type="button" class="btn btn-success" id="btnSave" onclick="saveClaim();">บันทึก</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">   
function setPageForClaimDetail(claimId){
	$.ajax({
		url : '${pageContext.request.contextPath}/claim/find?id=' + claimId,
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(result) {
			$.each( result, function( key, value ) {
				  $("#modalClaimDetail").find("#" + key).val(value);
			});
			
			$("#modalClaimDetail").find("#coArea").prop( "checked", result.coArea );
			$("#modalClaimDetail").find("#disperse").prop( "checked", result.disperse );
			
			if($("#modalClaimDetail").find("#paySurveyCode").val() != ""){
				$("#modalClaimDetail").find("#surveyOther").prop( "disabled", true);
				$("#modalClaimDetail").find("#surveyFine").prop( "disabled", true);
			}else{
				$("#modalClaimDetail").find("#surveyOther").prop( "disabled", false);
				$("#modalClaimDetail").find("#surveyFine").prop( "disabled", false);
			}
		}
	});
}

function saveClaim(){
	$.ajax({
		url : '${pageContext.request.contextPath}/claim/save',
		type : "POST",
		data: { 
			claimId: $("#modalClaimDetail").find("#claimId").val(), 
			remark: $("#modalClaimDetail").find("#remark").val(), 
			surveyOther: $("#modalClaimDetail").find("#surveyOther").val(), 
			surveyFine: $("#modalClaimDetail").find("#surveyFine").val()
		} ,
		success : function(data) {
			if(data.message !=  ""){			
				alert(data.message);
			}
			
			setPageForClaimDetail($("#modalClaimDetail").find("#claimId").val());
			
			if(isSearch){
				delay(function(){
					tblClaimDt.fnDraw();
				}, 1000 );
			}
		}
	});
}
</script>