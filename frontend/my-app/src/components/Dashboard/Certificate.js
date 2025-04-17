import React from 'react';
import { useLocation } from 'react-router-dom';

const ResultPage = () => {
    const location = useLocation();
    const response = location.state?.response || {};

    const vc = response.vcResponse?.vc || {};
    const vcHash = response.vcResponse?.vcHash;
    const ssnHash = response.ssnHash;
    return (
        <div>
            <h2>Upload Result</h2>
            <p><strong>DID:</strong> {vc.did}</p>
            <p><strong>Name:</strong> {vc.name}</p>
            <p><strong>SSN Verification Status:</strong> {vc.ssnVerificationStatus}</p>
            <p><strong>VC Hash:</strong> {vcHash}</p>
            <p><strong>SSN Hash:</strong> {ssnHash}</p>
            <p><strong>Verified On:</strong> {vc.verifiedOn}</p>
            <p><strong>Expires On:</strong> {vc.expiresOn}</p>
            <p><strong>Verified By:</strong> {vc.verifiedBy}</p>
            
        </div>
    );
};

export default ResultPage;
