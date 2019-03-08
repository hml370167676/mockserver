package com.uusoft.test.mock.mockserver.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaseManager implements Serializable {

  private String caseName;

  private String caseBody;

  private static final long serialVersionUID = 1L;


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", caseName=").append(caseName);
    sb.append(", caseBody=").append(caseBody);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}