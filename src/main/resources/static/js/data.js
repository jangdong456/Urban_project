const url = "https://data.gm.go.kr/openapi/Apttradedelngdetail?KEY=fd588635f78f47f0b90354625482c963&Type=json&pIndex=1&pSize=1"

fetch(url, {
    method : "GET",
    
})
.then(res => res.json())
.then(res => {
    console.log(res);
}).catch(error => {
    console.log("오류 :" + error);
});


"https://data.gm.go.kr/openapi/Apttradedelngdetail?KEY=fd588635f78f47f0b90354625482c963&Type=json&pIndex=1&pSize=1"

{"Apttradedelngdetail":
    [{"head":[{"list_total_count":24911},{"RESULT":{"CODE":"INFO-000","MESSAGE":"정상 처리되었습니다."}}]},
    {"row":[
        {"YY":"2016","DELNG_AMT":25500,"LEGALDONG_LOTNO_CD":"1","LEGALDONG_EMD_TYPE_CD":"10300","LEGALDONG_SIGNGU_TYPE_CD":"41210",
            "LEGALDONG_VICENO_TYPE_CD":"0000","LEGALDONG_ORIGNO_TYPE_CD":"0110","LEGALDONG_NM":"하안동","ROADNM_DIV_CD":"3187057",
            "ROADNM_GROUND_UNDGRND_CD":"0","ROADNM_SN_TYPE_CD":"02","ROADNM_SIGNGU_TYPE_CD":"41210","ROADNM_BULDNG_VICENO_TYPE_CD":"00000",
            "ROADNM_BULDNG_ORIGNO_TYPE_CD":"00284","ROADNM":"하안로","FLOOR_CNT":8,"LOTNO":"110","MANAGE_NO":"41210-16","APT_NM":"주공12",
            "BUILD_YY":"1990","DE":"01","MT":"11","PRVTUSE_AR":44.89}]
    }]  
}


건물명 : APT_NM
면적 : PRVTUSE_AR
지번 : LOTNO
실거래가 : DELNG_AMT
층 : FLOOR_CNT
건축년도 : BUILD_YY


