package com.example.adaprobation02.data.mapper

import com.example.adaprobation02.data.database.model.CCNMComp
import com.example.adaprobation02.data.database.model.CCNMCompL
import com.example.adaprobation02.data.database.model.CCNMDistrict
import com.example.adaprobation02.data.database.model.CCNMDistrictL
import com.example.adaprobation02.data.database.model.CCNMPovince
import com.example.adaprobation02.data.database.model.CCNMPovinceL
import com.example.adaprobation02.data.remote.model.districtResponse.CDistrictResponse
import com.example.adaprobation02.data.remote.model.povinceResponse.CPovinceResponse
import com.example.adaprobation02.data.remote.model.userResponse.CUserResponse

fun CUserResponse.C_SETxToCCNMCompLList(): List<CCNMCompL> {
    return this.aoItem.aoCompLng.map { raCompLng ->
        CCNMCompL(
            tCmpCode = raCompLng.tCmpCode,
            tLngID = raCompLng.nLngID,
            tCmpName = raCompLng.tCmpName,
            tCmpShop = raCompLng.tCmpShop,
            tCmpDirector = raCompLng.tCmpDirector
        )
    }
}


fun CUserResponse.C_SETxToCCNMCompList(): List<CCNMComp> {
    return this.aoItem.aoComp.map { raCompItem ->
        CCNMComp(
            tCmpCode = raCompItem.tCmpCode,
            tCmpTel = raCompItem.tCmpTel,
            tCmpFax = raCompItem.tCmpFax,
            tBchcode = raCompItem.tBchcode,
            tCmpWhsInOrEx = raCompItem.tCmpWhsInOrEx,
            tCmpRetInOrEx = raCompItem.tCmpRetInOrEx,
            tCmpEmail = raCompItem.tCmpEmail,
            tRteCode = raCompItem.tRteCode,
            tVatCode = raCompItem.tVatCode,
            tLastUpdOn = raCompItem.tLastUpdOn,
            tLastUpdBy = raCompItem.tLastUpdBy,
            tCreateOn = raCompItem.tCreateOn,
            tCreateBy = raCompItem.tCreateBy
        )
    }
}

fun CPovinceResponse.C_SETxToCCNMPovinces(): List<CCNMPovince> {
    return this.aoItem.aoPvn.map { raPvn ->
        CCNMPovince(
            tPvnCode = raPvn.tPvnCode ?: "",
            tZneCode = raPvn.tZneCode,
            tPvnLatitude = raPvn.tPvnLatitude,
            tPvnLongitude = raPvn.tPvnLongitude,
            tLastUpdOn = raPvn.tLastUpdOn,
            tLastUpdBy = raPvn.tLastUpdBy,
            tCreateOn = raPvn.tCreateOn,
            tCreateBy = raPvn.tCreateBy
        )
    }
}


fun CPovinceResponse.C_SETxToCCNMPovinceL(): List<CCNMPovinceL> {
    return this.aoItem.aoPvnLng.map { raPvnLng ->
        CCNMPovinceL(
            tPvnCode = raPvnLng.tPvnCode,
            nLngID = raPvnLng.nLngID,
            tName = raPvnLng.tPvnName
        )
    }
}

fun CDistrictResponse.C_SETxToCCNMDistrict(): List<CCNMDistrict> {
    return this.aoItem.aoDistrinct.map { raDistrinct ->
        CCNMDistrict(
            tDstCode = raDistrinct.tDstCode,
            tDstPost = raDistrinct.tDstPost,
            tPvnCode = raDistrinct.tPvnCode,
            tDstLatitude = raDistrinct.tDstLatitude,
            tDstLongitude = raDistrinct.tDstLongitude,
            tLastUpdOn = raDistrinct.tLastUpdOn,
            tLastUpdBy = raDistrinct.tLastUpdBy,
            tCreateOn = raDistrinct.tCreateOn,
            tCreateBy = raDistrinct.tCreateBy
        )
    }
}

fun CDistrictResponse.C_SETxToCCNMDistrictL(): List<CCNMDistrictL> {
    return this.aoItem.aoDistrinctLng.map { raDistrinctLng ->
        CCNMDistrictL(
            tDstCode = raDistrinctLng.tDstCode,
            tLngID = raDistrinctLng.nLngID,
            tDstName = raDistrinctLng.tDstName
        )
    }
}



