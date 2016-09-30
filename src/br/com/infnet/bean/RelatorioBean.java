package br.com.infnet.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.infnet.dao.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@ManagedBean
public class RelatorioBean {
	
	public void gerarRelatorioFilmes() throws SQLException, JRException, IOException{		
		
		String path = "C:\\Users\\Yago\\workspace\\Yago_Schuenck_AV2\\";
		
		JasperCompileManager.compileReport(path + "relatorio_av2_sisfiscon.jrxml");
		
		Connection conn = new ConnectionFactory().getConnection();
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Cache-Control", "max-age=0");
		response.setContentType("application/pdf");		
		
		JasperPrint print = JasperFillManager.fillReport(path + "relatorio_av2_sisfiscon.jasper", null, conn);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, baos);
		OutputStreamExporterOutput outputStream = new SimpleOutputStreamExporterOutput(path + "relatorio-av2-" + new Date().getTime() + ".pdf");

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(outputStream);
		exporter.exportReport();
		out.write(baos.toByteArray());
		out.flush();
		out.close();
		FacesContext.getCurrentInstance().responseComplete();
		conn.close();	
		
	}
}
